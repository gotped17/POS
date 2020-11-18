function validate() {
	var fail = false;
	const facherror = document.getElementById("fachError");
	if (!document.change.fach.value.trim()) {
		facherror.textContent = "Fach eingeben!";
		fail = true;
	} else if (facherror.textContent.trim().length > 0) {
		facherror.textContent = "";
	}

	const lehrererror = document.getElementById("lehrerError");
	if (!document.change.lehrer.value.trim()) {
		lehrererror.textContent = "Lehrer eingeben!";
		fail = true;
                
	} else if (lehrererror.textContent.trim().length > 0) {
		lehrererror.textContent = "";
	}
	return !fail;
}