$(function () {
    $(document).on('click', '.js-rule-action', function (evt) {
        var action = evt.originalEvent.srcElement.dataset.action;
        var $ctn = $(evt.currentTarget).parent();
        switch (action) {
            case 'validate':
                $()
                break;
            case 'test':
                break;
            case 'copy':
                if (confirm('确定复制吗?')) {
                    $ctn.find('.js-form-copy').submit();
                }
                break;
            case 'export':
                if (confirm('确定导出吗?')) {
                    $ctn.find('.js-form-export').submit();
                }
                break;
            case 'delete':
                if (confirm('确定删除吗?')) {
                    $ctn.find('.js-form-delete').submit();
                }
                break;
        }
    });
});