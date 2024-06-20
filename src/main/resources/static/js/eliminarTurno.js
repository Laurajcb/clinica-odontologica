window.addEventListener("load", function () {
  const url = "http://localhost:8080/";
  const btnEliminarTurno = document.getElementById("eliminarTurno");

  btnEliminarTurno.addEventListener("click", function () {
    const turnoId = JSON.parse(localStorage.getItem("turno")).id;

    const configuraciones = {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    };

    eliminarTurno(turnoId, configuraciones);
  });

  function eliminarTurno(turnoId, configuraciones) {
    fetch(`${url}turnos/eliminar/?id=${turnoId}`, configuraciones)
      .then((response) => {
        if (response.ok) return response;
        return Promise.reject(response);
      })
      .then((data) => {
        alert("Turno eliminado con éxito");
      })
      .catch((error) => {
        console.error(error);
        alert("Error al eliminar el turno. Por favor inténtelo nuevamente");
      });
  }
});
