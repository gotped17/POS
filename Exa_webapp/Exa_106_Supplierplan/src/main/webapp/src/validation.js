function validate(){
    var lehrer = document.getElementById("Lehrer");
    if(length(lehrer) === 2){
        return true;
    }else if (!lehrer) {
        alert("Lehrer eingeben");
    }
    else{
        alert("Bitte Lehrerkürzel eingeben");
        return false;
    }
    var fach = document.getElementById("Fach");
    if(!fach){
        alert("Bitte Fach eingeben");
        return false;
    }else{
        return true;
    }
}
