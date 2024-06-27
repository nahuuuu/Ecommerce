import React, { useState } from 'react';
import axios from 'axios';

const ImageForm = () => {
    const [file, setFile] = useState(null);

    const handleFileChange = (event) => {
        setFile(event.target.files[0]);
    };

    const handleSubmit = (event) => {
        event.preventDefault();

        const formData = new FormData();
        formData.append('file', file);

        axios.post('/api/upload', formData)
            .then((response) => {
                console.log('Imagen subida con éxito:', response.data);
            })
            .catch((error) => {
                console.error('Error al subir la imagen:', error);
            });
    };

    return (
        <div>
            <form>
                <div className="form-group">
                    <label htmlFor="file">Seleccionar imagen:</label>
                    <input
                        type="file"
                        id="file"
                        accept=".jpg, .png"
                        onChange={handleFileChange}
                    />
                    <button onClick={handleSubmit}>Subir</button>
                </div>
            </form>
        </div>
    );
};

export default ImageForm;
