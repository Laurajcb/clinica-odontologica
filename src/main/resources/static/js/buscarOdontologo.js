window.addEventListener("load", function () {
  const url = "http://localhost:8080/";
  const form = document.getElementById("buscarOdontologoForm");
  const moduloInformacionOdontologo = document.getElementById("moduloInformacionOdontologo");
  const moduloBuscarOdontologo = document.getElementById("moduloBuscarOdontologo");
  let odontologo;

  form.addEventListener("submit", function (event) {
    event.preventDefault();

    const odontologoId = document.getElementById("selectOdontologos").value;

    buscarOdontologo(odontologoId);
    moduloBuscarOdontologo.style.display = "none";
    form.reset();
  });

  function buscarOdontologo(odontologoId) {
    fetch(`${url}odontologos/listar/${odontologoId}`)
      .then((response) => {
        if (response.ok) return response.json();
        return Promise.reject(response);
      })
      .then((data) => {
        odontologo = data;
        localStorage.setItem("odontologo", JSON.stringify(data));
        mostrarInfoOdontologo();
      })
      .catch((error) => {
        console.error(error);
      });
  }

  function mostrarInfoOdontologo() {
    limpiarModuloInformacionOdontologo();
    moduloInformacionOdontologo.style.display = "block";

    const nombreOdontologo = document.getElementById("nombreOdontologo");
    const spanNombreOdontologo = document.createElement("span");
    spanNombreOdontologo.textContent = odontologo.apellido + ' ' + odontologo.nombre;
    nombreOdontologo.appendChild(spanNombreOdontologo)

    const matriculaOdontologo = document.getElementById("matriculaOdontologo");
    const spanMatriculaOdontologo = document.createElement("span");
    spanMatriculaOdontologo.textContent = odontologo.matricula;
    matriculaOdontologo.appendChild(spanMatriculaOdontologo);
  }

  function limpiarModuloInformacionOdontologo() {
    removeAllChildSpans(document.getElementById("nombrePaciente"));
    removeAllChildSpans(document.getElementById("matriculaOdontologo"));
  }

  function removeAllChildSpans(element) {
    const spans = element.querySelectorAll("span");
    spans.forEach(span => span.remove());
  }
});
