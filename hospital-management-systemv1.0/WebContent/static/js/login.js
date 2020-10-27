/**
 * 
 */

window.onload = function(){
	
}

function check()
{
    let result = false;
    
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    let message = document.getElementById("message");
    
    if(username === "" && password === ""){
        alert("Please enter username and password");
        message.innerHTML = "<p>Please enter username and password</p>";
        result =  false;
    }
    
    else if(username === ""){
        alert("Please enter the username");
        message.innerHTML = "<p>Username Cannot be empty</p>";
        result =  false;
    }
    else if(password === ""){
        alert("Please enter the password");
        message.innerHTML = "<p>Password Cannot be empty</p>";
        result = false;
    }

    else{
        result = true;
    }

    return result;
}
