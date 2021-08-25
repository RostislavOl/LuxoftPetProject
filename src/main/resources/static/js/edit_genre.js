$(document).ready(function() {

    $('.table .btn').on('click',function(event) {

        event.preventDefault();

        var href = $(this).attr('href');

        $.get(href, function (genre, status){
           $('#Id').val(genre.id);
           $('#genreName').val(genre.genreName);
        });

        $('#editModal').modal();

    });

});