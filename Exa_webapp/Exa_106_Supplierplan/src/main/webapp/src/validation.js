function validate(){
    var lehrer = document.getElementById("Lehrer");
    if(length(lehrer) === 2){
        return true;
    }else if (!lehrer) {
        document.getElementById("lehrerError").innerHTML = "Bitte Lehrerkürzel eingeben";
    }
    else{
        document.getElementById("lehrerError").innerHTML = "Bitte Lehrerkürzel eingeben";
        return false;
    }
    var fach = document.getElementById("Fach");
    if(!fach){
        document.getElementById("fachError").innerHTML = "Bitte Fach eingeben";
        return false;
    }else{
        return true;
    }
}
