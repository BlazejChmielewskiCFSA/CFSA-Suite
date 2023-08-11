window.onload = function() {
    const priorityElements = document.querySelectorAll(".priority");
    priorityElements.forEach(function(element) {
        switch (element.textContent.toLowerCase()) {
        case "natychmiastowy":
            element.classList.add("natychmiastowy");
            break;
        case "pilny":
            element.classList.add("pilny");
            break;
        case "wysoki":
            element.classList.add("wysoki");
            break;
        case "normalny":
            element.classList.add("normalny");
            break;
        case "niski":
            element.classList.add("niski");
            break;
        default:
            break;
        }
      })

      const statusElements = document.querySelectorAll(".status");
      statusElements.forEach(function(element) {
              switch (element.textContent.toLowerCase()) {
              case "zamkniety":
                  element.classList.add("zamkniety");
                  break;
              case "rozwiazywany":
                  element.classList.add("rozwiazywany");
                  break;
              case "oczekujacy":
                  element.classList.add("oczekujacy");
                  break;
              default:
                  break;
              }
            })

      const departmentElements = document.querySelectorAll(".department");
      departmentElements.forEach(function(element) {
              switch (element.textContent.toLowerCase()) {
              case "baio":
                  element.classList.add("baio");
                  break;
              case "bi":
                  element.classList.add("bi");
                  break;
              case "dwwdz":
                  element.classList.add("dwwdz");
                  break;
              case "dwwd":
                  element.classList.add("dwwd");
                  break;
              default:
                  break;
              }
            })

      };