function validate() {
    nickname = document.getElementById("nickname");
    email = document.getElementById("email");
    comment = document.getElementById("comment");
    if(!nickname)
        return false;
    if((!email || !email.includes("@")))
        return false;
    if(!comment)
        return false;
    return true;
}
