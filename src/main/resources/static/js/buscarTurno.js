window.addEventListener("load", function () {
  const url = "http://localhost:8080/";
  const form = document.getElementById("buscarTurnoForm");
  const moduloInformacionTurno= document.getElementById("moduloInformacionTurno");
  const moduloBuscarTurno = document.getElementById("moduloBuscarTurno");
  let turno;

  form.addEventListener("submit", function (event) {
    event.preventDefault();

    const turnoId = document.getElementById("selectTurnos").value;

    buscarTurno(turnoId);
    moduloBuscarTurno.style.display = "none";
    form.reset();
  });

  function buscarTurno(turnoId) {
    fetch(`${url}turnos/listar/${turnoId}`)
      .then((response) => {
        if (response.ok) return response.json();
        return Promise.reject(response);
      })
      .then((data) => {
        turno = data;
        localStorage.setItem("turno", JSON.stringify(data));
        mostrarInfoTurno();
      })
      .catch((error) => {
        console.error(error);
      });
  }

  function mostrarInfoTurno() {
    limpiarModuloInformacionTurno();
    moduloInformacionTurno.style.display = "block";

    const fechaYHoraTurno = document.getElementById("fechaYHoraTurno");
    const spanFechaYHoraTurno = document.createElement("span");
    spanFechaYHoraTurno.textContent = turno.fechaYHora.substring(0, 10) + ' ' + turno.fechaYHora.substring(11, 16);
    fechaYHoraTurno.appendChild(spanFechaYHoraTurno)

    const odontologoTurno = document.getElementById("odontologoTurno");
    const spanOdontologoTurno = document.createElement("span");
    spanOdontologoTurno.textContent = turno.odontologo.apellido + ' ' + turno.odontologo.nombre;
    odontologoTurno.appendChild(spanOdontologoTurno);

    const pacienteTurno = document.getElementById("pacienteTurno");
    const spanPacienteTurno = document.createElement("span");
    spanPacienteTurno.textContent = turno.paciente.apellido + ' ' + turno.paciente.nombre;
    pacienteTurno.appendChild(spanPacienteTurno);
  }

  function limpiarModuloInformacionTurno() {
    removeAllChildSpans(document.getElementById("fechaYHoraTurno"));
    removeAllChildSpans(document.getElementById("odontologoTurno"));
    removeAllChildSpans(document.getElementById("pacienteTurno"));
  }

  function removeAllChildSpans(element) {
    const spans = element.querySelectorAll("span");
    spans.forEach(span => span.remove());
  }
});
