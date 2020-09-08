var main = {
    init : function () {
        var _this = this;
        //  POSTS
        $('#posts-btn-save').on('click', function () {
            _this.posts_save();
        });

        $('#posts-btn-update').on('click', function () {
            _this.posts_update();
        });

        $('#posts-btn-delete').on('click', function () {
            _this.posts_delete();
        });
        //  COUNSELOR
        $('#counselor-btn-save').on('click', function () {
            _this.counselor_save();
        });

        $('#counselor-btn-update').on('click', function () {
            _this.counselor_update();
        });

        $('#counselor-btn-delete').on('click', function () {
            _this.counselor_delete();
        });
        //  CLIENT
        $('#client-btn-save').on('click', function () {
            _this.client_save();
        });

        $('#client-btn-update').on('click', function () {
            _this.client_update();
        });

        $('#client-btn-delete').on('click', function () {
            _this.client_delete();
        });
    },
    //  POSTS
    posts_save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            client: $('#client').val(),
            content: $('#content').val(),
            images: $('#images').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/posts/index';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    posts_update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/posts/index';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    posts_delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/posts/index';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    //  COUNSELOR
    counselor_save : function () {
        var data = {
            age: $('#age').val(),
            name: $('#name').val(),
            job: $('#job').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/counselor',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('상담자 정보가 등록되었습니다.');
            window.location.href = '/counselor/index';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    counselor_update : function () {
        var data = {
            age: $('#age').val(),
            name: $('#name').val(),
            job: $('#job').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/counselor/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('상담자 정보가 수정되었습니다.');
            window.location.href = '/counselor/index';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    counselor_delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/counselor/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('상담자 정보가 삭제되었습니다.');
            window.location.href = '/counselor/index';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    //  CLIENT
    client_save : function () {
        var data = {
            age: $('#age').val(),
            name: $('#name').val(),
            job: $('#job').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/client',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('내담자 정보가 등록되었습니다.');
            window.location.href = '/client/index';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    client_update : function () {
        var data = {
            age: $('#age').val(),
            name: $('#name').val(),
            job: $('#job').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/client/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('내담자 정보가 수정되었습니다.');
            window.location.href = '/client/index';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    client_delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/client/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('내담자 정보가 삭제되었습니다.');
            window.location.href = '/client/index';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();