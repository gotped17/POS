const all_pizzas = ['Margherita', 'Salami', 'Prosciutto', 'Rucola', 'Hawaii'];
function validate() {
    var valid = false;
    var selectedValues = [];
    all_pizzas.forEach((pizza) => {
        var selectBox = document.getElementById(pizza);
        var selected = selectBox.options[selectBox.selectedIndex].value;
        selectedValues.push(selected);
    });
    selectedValues.forEach((item) => {
        console.log(item);
        if(item > 0){
            valid = true;
        }
    });
    if(!valid){
        alert('Bitte mindestens eine Pizza bestellen');
    }
    console.log(valid);
    return valid;
}