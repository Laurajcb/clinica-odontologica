window.addEventListener("load", function () {
  const url = "http://localhost:8080/";
  const btnEliminarPaciente = document.getElementById("eliminarPaciente");

  btnEliminarPaciente.addEventListener("click", function () {
    const pacienteId = JSON.parse(localStorage.getItem("paciente")).id;

    const configuraciones = {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    };

    eliminarPaciente(pacienteId, configuraciones);
  });

  function eliminarPaciente(pacienteId, configuraciones) {
    fetch(`${url}pacientes/eliminar/?id=${pacienteId}`, configuraciones)
      .then((response) => {
        if (response.ok) return response;
        return Promise.reject(response);
      })
      .then((data) => {
        alert("Paciente eliminado con éxito");
      })
      .catch((error) => {
        console.error(error);
        alert("Error al eliminar el paciente. Por favor inténtelo nuevamente");
      });
  }
});
