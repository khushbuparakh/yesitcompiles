<?php
 
require_once '../include/DbHandler.php';
require_once '../include/PassHash.php';
require '.././libs/Slim/Slim.php';
 
\Slim\Slim::registerAutoloader();
 
$app = new \Slim\Slim();
 
// User id from db - Global Variable
$user_id = NULL;
 
/**
 * Verifying required params posted or not
 */
function verifyRequiredParams($required_fields) {
    $error = false;
    $error_fields = "";
    $request_params = array();
    $request_params = $_REQUEST;
    // Handling PUT request params
    if ($_SERVER['REQUEST_METHOD'] == 'PUT') {
        $app = \Slim\Slim::getInstance();
        parse_str($app->request()->getBody(), $request_params);
    }
    foreach ($required_fields as $field) {
        if (!isset($request_params[$field]) || strlen(trim($request_params[$field])) <= 0) {
            $error = true;
            $error_fields .= $field . ', ';
        }
    }
 
    if ($error) {
        // Required field(s) are missing or empty
        // echo error json and stop the app
        $response = array();
        $app = \Slim\Slim::getInstance();
        $response["error"] = true;
        $response["message"] = 'Required field(s) ' . substr($error_fields, 0, -2) . ' is missing or empty';
        echoRespnse(400, $response);
        $app->stop();
    }
}
 
/**
 * Validating email address
 */
function validateEmail($email) {
    $app = \Slim\Slim::getInstance();
    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        $response["error"] = true;
        $response["message"] = 'Email address is not valid';
        echoRespnse(400, $response);
        $app->stop();
    }
}
 
/**
 * Echoing json response to client
 * @param String $status_code Http response code
 * @param Int $response Json response
 */
function echoRespnse($status_code, $response) {
    $app = \Slim\Slim::getInstance();
    // Http response code
    $app->status($status_code);
 
    // setting response content type to json
    $app->contentType('application/json');
 
    echo json_encode($response);
}

/**
 * User Registration
 * url - /register
 * method - POST
 * params - name, email, password
 */
$app->post('/register', function() use ($app) {
            // check for required params
            verifyRequiredParams(array('name', 'email', 'password' , 'first_name' , 'last_name' , 'date_of_birth' , 'role' , 'gender' , 'city'));
 
            $response = array();
 
            // reading post params
            $name = $app->request->post('name');
            $email = $app->request->post('email');
            $password = $app->request->post('password');
			$first_name = $app->request->post('first_name');
			$last_name = $app->request->post('last_name');
			$date_of_birth = $app->request->post('date_of_birth');
			$role = $app->request->post('role');
			$gender = $app->request->post('gender');
			$city = $app->request->post('city');
 
            // validating email address
            validateEmail($email);
 
            $db = new DbHandler();
            $res = $db->createUser($name, $email, $password, $first_name, $last_name, $date_of_birth, $role, $gender, $city);
 
            if ($res == USER_CREATED_SUCCESSFULLY) {
                $response["error"] = false;
                $response["message"] = "You are successfully registered";
                echoRespnse(201, $response);
            } else if ($res == USER_CREATE_FAILED) {
                $response["error"] = true;
                $response["message"] = "Oops! An error occurred while registereing";
                echoRespnse(200, $response);
            } else if ($res == USER_ALREADY_EXISTED) {
                $response["error"] = true;
                $response["message"] = "Sorry, this email already existed";
                echoRespnse(200, $response);
            }
        });
		

 
$app->post('/addproject', function() use ($app){
	// check for required params
            verifyRequiredParams(array('name', 'start_date', 'end_date' , 'description' , 'team_lead','target_audience' , 'estimated_funds'));
 
            $response = array();
			
			$name = $app->request->post('name');
            $start_date = $app->request->post('start_date');
            $end_date = $app->request->post('end_date');
			$description = $app->request->post('description');
			$team_lead = $app->request->post('team_lead');
			$target_audience = $app->request->post('target_audience');
			$estimated_funds = $app->request->post('estimated_funds');
			
			$db = new DbHandler();
			$res = $db->addProject($name, $start_date, $end_date, $description, $team_lead,$target_audience, $estimated_funds);
			
			if ($res != PROJECT_CREATE_FAILED){
				$response["error"] = false;
                $response["message"] = "project successfully registered with id" + $res;
				echoRespnse(201, $response);
			} else {
				$response["error"] = TRUE;
                $response["message"] = "oops! an error occured while registering project";
				echoRespnse(200, $response);
			}
});

$app->get('/projects/:id',  function($project_id) {
            global $user_id;
            $response = array();
            $db = new DbHandler();
 
            // fetch project
            $result = $db->getProject($project_id);
 
            if ($result != NULL) {
                $response["error"] = false;
                $response["id"] = $result["id"];
                $response["name"] = $result["name"];
                $response["start_date"] = $result["start_date"];
                $response["end_date"] = $result["end_date"];
				$response["description"] = $result["description"];
				$response["team_lead"] = $result["team_lead"];
				$response["estimated_funds"] = $result["estimated_funds"];
                echoRespnse(200, $response);
            } else {
                $response["error"] = true;
                $response["message"] = "The requested resource doesn't exists";
                echoRespnse(404, $response);
            }
        });
$app->run();

?>