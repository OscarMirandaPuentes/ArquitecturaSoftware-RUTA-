function quitarEscalaGrises(imageId) {
    const image = document.getElementById(imageId);
    if (image) {
        image.style.filter = 'grayscale(0%)';
    }
}

//quitarEscalaGrises('I2E2');