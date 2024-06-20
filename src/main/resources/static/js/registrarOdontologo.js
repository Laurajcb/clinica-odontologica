window.addEventListener("load", function () {
  const url = "http://localhost:8080/";
  const form = document.getElementById("registrarOdontologoForm");

  form.addEventListener("submit", function (event) {
    event.preventDefault();

    const nombre = document.getElementById("nombreFormOdontologo").value;
    const apellido = document.getElementById("apellidoFormOdontologo").value;
    const matricula = document.getElementById("matricula").value;

    const datos = {
      nombre: capitalizeFirstLetter(nombre),
      apellido: capitalizeFirstLetter(apellido),
      matricula: matricula,
    };

    const configuraciones = {
      method: "POST",
      body: JSON.stringify(datos),
      headers: {
        "Content-Type": "application/json",
      },
    };

    crearOdontologo(configuraciones);
    form.reset();
  });

  function crearOdontologo(configuraciones) {
    fetch(`${url}odontologos/registrar`, configuraciones)
      .then((response) => {
        if (response.ok) return response.json();
        return Promise.reject(response);
      })
      .then((data) => {
          alert("Odontólogo registrado con éxito")
      })
      .catch((error) => {
        console.error(error);
        alert("Error al registrar el odontólogo. Por favor inténtelo nuevamente")
      });
  }

  function capitalizeFirstLetter (string) {
    return string.charAt(0).toUpperCase() + string.slice(1).toLowerCase()
  }
});
