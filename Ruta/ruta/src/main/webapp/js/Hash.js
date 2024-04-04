class Hash{
    async calcularHash(texto) {
        const encoder = new TextEncoder();
        const data = encoder.encode(texto);
        
        const hashBuffer = await crypto.subtle.digest('SHA-256', data);
        const hashArray = Array.from(new Uint8Array(hashBuffer));
        const hashHex = hashArray.map(byte => byte.toString(16).padStart(2, '0')).join('');
    
        return hashHex;
    }
    
    // Ejemplo de uso:
    /*const texto = 'contraseña123';
    calcularHash(texto)
        .then(hash => console.log('Hash de la contraseña:', hash))
        .catch(error => console.error('Error al calcular el hash:', error));*/
    
}