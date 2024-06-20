window.addEventListener("load", function () {
  const url = "http://localhost:8080/";
  const btnEliminarOdontologo = document.getElementById("eliminarOdontologo");

  btnEliminarOdontologo.addEventListener("click", function () {
    const odontologoId = JSON.parse(localStorage.getItem("odontologo")).id;

    const configuraciones = {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    };

    eliminarOdontologo(odontologoId, configuraciones);
  });

  function eliminarOdontologo(odontologoId, configuraciones) {
    fetch(`${url}odontologos/eliminar/?id=${odontologoId}`, configuraciones)
      .then((response) => {
        if (response.ok) return response;
        return Promise.reject(response);
      })
      .then((data) => {
        alert("Odontólogo eliminado con éxito");
      })
      .catch((error) => {
        console.error(error);
        alert("Error al eliminar el odontólogo. Por favor inténtelo nuevamente");
      });
  }
});
