function confirmPopUp(id) {
    let text = "Press a button!\nEither OK or Cancel.";
    if (confirm(text)) {
        document.getElementById("removeOrder" + id).submit();
    } else {

    }
    console.log(text)
}