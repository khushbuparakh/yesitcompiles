function addUser(){
    var firstname  = document.getElementById('firstname').value
    var firstname  = document.getElementById('lastname').value
    var firstname  = document.getElementById('email').value
    var firstname  = document.getElementById('role').value
    var firstname  = document.getElementById('gender').value
    var firstname  = document.getElementById('city').value
    var firstname  = document.getElementById('gender').value

    $.ajax({
                url: "http://localhost/ProjectManagmentAPI/Source/v1/register",
                datatype: "JSON",
                type: "Post",
                success: function (data) {
                    debugger;
                    for(var i=0;i<data.length;i++)
                    {
                        var opt = new Option(data[i].Bname);
                        $("#op1").append(opt);
                    }
                }
            });
}