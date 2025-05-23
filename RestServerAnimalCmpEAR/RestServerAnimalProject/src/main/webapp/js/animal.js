$(document).ready(function() {
	// IP och väder
	$.ajax({
		method: "GET",
		url: "http://api.ipstack.com/check?access_key=1869fb0fd8720b22ba010e370f0e9550",
		error: ajaxReturn_Error,
		success: ajaxReturn_Success
	});

	function ajaxReturn_Success(result, status, xhr) {
		ParseJsonFile(result);
	}

	function ajaxReturn_Error(result, status, xhr) {
		console.log("Ajax-weather error " + status);
	}



	// hitta djur 
	$("#FindBtn").click(function() {
		var strValue = $("#id").val();
		if (strValue != "") {
			$.ajax({
				method: "GET",
				url: "http://localhost:8080/OwnerClientProject/Animals/" + strValue,
				error: ajaxFindReturnError,
				success: ajaxFindReturnSuccess
			});

			function ajaxFindReturnSuccess(result, status, xhr) {
				ParseJsonFileAnimal(result);

				updateAnimalInfoSection(result);
			}

			function ajaxFindReturnError(result, status, xhr) {
				alert("Error");
				console.log("Ajax-find animal: " + status);
			}
		}
	})

	// radera djur
	$("#DeleteBtn").click(function() {
		var strValue = $("#id").val();
		if (strValue != "") {
			$.ajax({
				method: "DELETE",
				url: "http://localhost:8080/OwnerClientProject/Animals/" + strValue,
				error: ajaxDelReturnError,
				success: ajaxDelReturnSuccess
			});

			function ajaxDelReturnSuccess(result, status, xhr) {
				clearFields();
				clearAnimalInfoSection();
				$("#name").attr("placeholder", "Animal deleted");
			}

			function ajaxDelReturnError(result, status, xhr) {
				alert("Error");
				console.log("Ajax-delete animal: " + status);
			}
		}
	});

});

// Väder 
function ParseJsonFile(result) {
	var lat = result.latitude;
	var long = result.longitude;
	var city = result.city;
	var ipNbr = result.ip;

	$("#city").text(city);
	$("#ipNbr").text(ipNbr);

	$.ajax({
		method: "GET",
		url: "http://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + long + "&units=metric&appid=ccdb9e39c219927878a7f1d574670b0c",
		error: ajaxWeatherReturn_Error,
		success: ajaxWeatherReturn_Success
	});

	function ajaxWeatherReturn_Success(result, status, xhr) {
		var sunrise = result.sys.sunrise;
		var sunset = result.sys.sunset;
		var sunriseDate = new Date(sunrise * 1000);
		var timeStrSunrise = sunriseDate.toLocaleTimeString("sv-SE");
		var sunsetDate = new Date(sunset * 1000);
		var timeStrSunset = sunsetDate.toLocaleTimeString("sv-SE");

		$("#sunrise").text("Sunrise: " + timeStrSunrise);
		$("#sunset").text("Sunset: " + timeStrSunset);
		$("#weather").text(result.weather[0].main);
		$("#degree").text(result.main.temp + " \u2103");
	}

	function ajaxWeatherReturn_Error(result, status, xhr) {
		alert("Error i OpenWeaterMap Ajax");
		console.log("Ajax weather error: " + status);
	}
}


function ParseJsonFileAnimal(result) {
	$("#id").val(result.id);
	$("#name").val(result.name);
	$("#species").val(result.species);
}

function clearFields() {
	$("#id").val("");
	$("#name").val("");
	$("#species").val("");
}


function updateAnimalInfoSection(result) {
	if (result && result.id) {
		$("#animal-id").text(result.id);
		$("#animal-name").text(result.name);
		$("#animal-species").text(result.species);


		$("#animal-info").show();
	}
}


function clearAnimalInfoSection() {
	$("#animal-id").text("");
	$("#animal-name").text("");
	$("#animal-species").text("");


}
