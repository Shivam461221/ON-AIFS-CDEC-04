
function signUp(){
    let username = document.getElementById('username').value;
    let email = document.getElementById('email').value;
    let password = document.getElementById('password').value;

    if(username=='' || email=='' || password==''){
        alert('All fields are mandatory');
        return;
    }
    let users = JSON.parse(localStorage.getItem("users")) || [] ;
    let existingUser = users.find(user=>user.email===email);

    if(existingUser){
        alert('Email already exist');
        return;
    }
    let newUser = {username, email, password};
    users.push(newUser);
    localStorage.setItem("users", JSON.stringify(users));
    alert('Sign Up successful');
    window.location.href = 'login.html';
}

function login(){
    let email = document.getElementById('email').value;
    let password = document.getElementById('password').value;
    if(email=='' || password==''){
        alert('All fields are mandatory');
        return;
    }

    let users = JSON.parse(localStorage.getItem("users")) || [] ;

    let foundUser = users.find(user=>user.email===email && user.password===password);

    if(foundUser){
            localStorage.setItem("loggedInUser", JSON.stringify(foundUser));
            window.location.href = 'dashboard.html';
    }
    else{
        alert('Wrong credentials');
    }

}