
    async function  calcularHash(texto) {
        const encoder = new TextEncoder();
        const data = encoder.encode(texto);
        
        const hashBuffer = await crypto.subtle.digest('SHA-256', data);
        const hashArray = Array.from(new Uint8Array(hashBuffer));
        const hashHex = hashArray.map(byte => byte.toString(16).padStart(2, '0')).join('');
    
        return hashHex;
    }
