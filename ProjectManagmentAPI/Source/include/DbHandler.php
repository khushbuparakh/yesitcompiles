<?php

/**
 * Class to handle all db operations
 * This class will have CRUD methods for database tables
 *
 * @author Ravi Tamada
 */
class DbHandler {

	private $conn;

	function __construct() {
		require_once dirname(__FILE__) . '/DbConnect.php';
		// opening db connection
		$db = new DbConnect();
		$this -> conn = $db -> connect();
	}

	/* ------------- `users` table method ------------------ */

	/**
	 * Creating new user
	 * @param String $name User full name
	 * @param String $email User login email id
	 * @param String $password User login password
	 */
	public function createUser($name, $email, $password, $first_name, $last_name, $date_of_birth, $role, $gender, $city) {
		require_once 'PassHash.php';
		$response = array();

		// First check if user already existed in db
		if (!$this -> isUserExists($email)) {
			// Generating password hash
			$password_hash = PassHash::hash($password);

			// Generating API key
			$api_key = $this -> generateApiKey();

			// insert query
			$stmt = $this -> conn -> prepare("INSERT INTO users(username, email, password,first_name,last_name,Date_of_birth,role,gender,city) values(?, ?, ?, ?, ?,?,?,?,?)");
			$stmt -> bind_param("sssssssss", $name, $email, $password_hash, $first_name, $last_name, $date_of_birth, $role, $gender, $city);

			$result = $stmt -> execute();

			$stmt -> close();

			// Check for successful insertion
			if ($result) {
				// User successfully inserted
				return USER_CREATED_SUCCESSFULLY;
			} else {
				// Failed to create user
				return USER_CREATE_FAILED;
			}
		} else {
			// User with same email already existed in the db
			return USER_ALREADY_EXISTED;
		}

		return $response;
	}
	
	public function addProject($name , $start_date ,  $end_date , $description ,  $team_lead , $target_audience, $estimated_funds){
		
		$today = date("Y-m-d");
		$stmt = $this -> conn -> prepare("INSERT INTO projects(name, start_date, end_date,description,team_lead,target_audience,created_at,is_active,estimated_funds) values(? , ? , ? , ? , ?, ? , ? , ?, ?)");
		$stmt -> bind_param("ssssissid", $name, $start_date, $end_date, $description, $team_lead, $target_audience , $today , intval(TRUE),$estimated_funds);

		$result = $stmt -> execute();

		$stmt -> close();
		
		if ($result){
			return $this -> conn -> insert_id;
		} else {
			return PROJECT_CREATE_FAILED;
		}

	}
	
	public function getProject($project_id){
        $stmt = $this->conn->prepare("SELECT p.id, p.name, p.start_date, p.end_date,p.team_lead,p.estimated_funds,p.description from projects p WHERE p.id = ? ");
        $stmt->bind_param("i", $project_id);
        if ($stmt->execute()) {
            $task = $stmt->get_result()->fetch_assoc();
            $stmt->close();
            return $task;
        } else {
            return NULL;
        }
    
	}
	
	private function isUserExists($email) {
        $stmt = $this->conn->prepare("SELECT id from users WHERE email = ?");
        $stmt->bind_param("s", $email);
        $stmt->execute();
        $stmt->store_result();
        $num_rows = $stmt->num_rows;
        $stmt->close();
        return $num_rows > 0;
    }
	
	private function generateApiKey() {
        return md5(uniqid(rand(), true));
    }

}
?>