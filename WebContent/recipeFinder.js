$(document).ready(
		function() {
			$("#submitButton").click(
					function(e) {
						var dataString = $("#recipeFinderForm").serialize();
						$.ajax({
							type : "POST",
							url : "recipe-finder",
							data : dataString,
							dataType : "json",
							success : function(data, textStatus, jqXHR) {

								if (data.success) {
									$("#ajaxResponse").html(
											"<b>" + data.message + "</b>");
								} else {
									$("#ajaxResponse").html(
											"<b><font color='red'>"
													+ data.errorMessage
													+ "</font></b>");
								}
							},
							error : function(jqXHR, textStatus, errorThrown) {
								console.log("Something really bad happened "
										+ textStatus);
								$("#ajaxResponse").html(
										"<b><font color='red'>"
												+ jqXHR.responseText
												+ "</font></b>");
							},
							beforeSend : function(jqXHR, settings) {
								$('#submitButton').attr("disabled", true);
							},
							complete : function(jqXHR, textStatus) {
								$('#submitButton').attr("disabled", false);
							}
						});
					});
		});