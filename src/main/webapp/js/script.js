function confirmPopUp(id) {
    let text = "Er du sikker på, at du vil fjerne denne ordre?\nBekræft med OK";
    if (confirm(text)) {
        document.getElementById("removeOrder" + id).submit();
    } else {

    }
    console.log(text)
}