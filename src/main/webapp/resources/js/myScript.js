/**
 * Created by german on 4/17/16.
 */
$(document).ready(function() {
    $.get( "/cage/load/cage/"+$("#cageId").val() +"/animals", function( data ) {
        console.log(data);
        var res = JSON.parse(data);
        $("#animalIdList").chosen({max_selected_options: res.capacity});
    });
    $("#cageId").on("change",function() {
        $.get( "/cage/load/cage/"+$("#cageId").val() +"/animals", function( data ) {
            console.log(data);
            var res = JSON.parse(data);
            $("#animalIdList").chosen("destroy").chosen({max_selected_options: res.capacity});
        });
    });
});