$(document).ready(function(){if($('.kanban-wrap').length>0){$(".kanban-wrap").sortable({connectWith:".kanban-wrap",handle:".kanban-box",placeholder:"drag-placeholder",update: function(event, ui) {
        var placeholderParent = $(ui.item).closest('.kanban-wrap');
        console.log(".drag-placeholder is now associated with the .kanban-wrap element with ID: " + placeholderParent.attr('id'));
    }});}
if($('.datetimepicker').length>0){$('.datetimepicker' +
    '').datetimepicker({format:'DD/MM/YYYY',icons:{up:"bx bx-chevron-up",down:"bx bx-chevron-down",next:'bx bx-chevron-right',previous:'bx bx-chevron-left'}});}});$(window).on('load',function(){$('#loader').delay(100).fadeOut('slow');$('#loader-wrapper').delay(500).fadeOut('slow');});