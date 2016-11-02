/* new */

function expandCollapseAll(elem){
    var step_details = elem.parentNode.parentNode.getElementsByClassName('step-details');
    var isExpanded = elem.getAttribute('data-isexpanded');
    if (isExpanded === 'false') {
        $(step_details).slideDown("fast");
        elem.setAttribute('data-isexpanded', 'true');
        var imgIcon = elem.parentNode.parentNode.getElementsByClassName('expand-img');
        $(imgIcon).addClass('collapse-img').removeClass('expand-img');
       /* $(elem).addClass('collapse-all-img').removeClass('expand-all-img');*/
    } else {
        $(step_details).slideUp("fast");
        elem.setAttribute('data-isexpanded', 'false');
        var imgIcon = elem.parentNode.parentNode.getElementsByClassName('collapse-img');
        $(imgIcon).addClass('expand-img').removeClass('collapse-img');
      /*  $(elem).addClass('expand-all-img').removeClass('collapse-all-img');  */
    }

}

function expColDetails(spnHead) {
    var stepDetNode = spnHead.parentNode.parentNode.getElementsByClassName("step-details")[0];
    if (spnHead.className == 'expand-img') {
        $(stepDetNode).slideDown("fast");
        spnHead.className = 'collapse-img';
    }
    else {
        $(stepDetNode).slideUp("fast");
        spnHead.className = 'expand-img';
    }
}

function failureShowMore(elem){
    var elmParent = findAncestor(elem,"show-more-container");
    var colPanel =  $(elmParent).find('.show-more-panel')[0];
    if(elem.className.indexOf('hidden') != -1){
        $(elem).removeClass('hidden');
        $(colPanel).slideDown('fast');
        $(elem).text('Show Less');
    }else{
        $(elem).addClass('hidden');
        $(colPanel).slideUp('fast');
        $(elem).text('Show More');
    }

}

function cbShowSteps(cb) {
    var elms = cb.parentNode.parentNode.parentNode.getElementsByClassName(cb.name);
    if (cb.checked) {
        $(elms).slideDown("fast");
    }
    else {
        $(elms).slideUp("fast");
    }
}

$(document).ready ( function(){
    var id  =  getParameterByName('id');
    if(id){
        console.log('Selected view: ' + id);
        showSelectedView('content-' + id);
    }
});

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function showSelectedView(content){
    $('[id*="content-"]').hide();
    $('#' + content).show();

    var node = document.getElementById(content).getElementsByClassName('content-header')[0];
    var yourHeight = 80;
    node.scrollIntoView(true);

    var scrolledY = window.scrollY;
    if(scrolledY){
        window.scroll(0, scrolledY - yourHeight);
    }

}

function selectview(elem) {
    $('#main-content .flow-content').hide();
    var content = elem.getAttribute('data-cont');

    showSelectedView(content);


}

function showHideProperties(elem){
    var isExpanded = elem.getAttribute('data-isexpanded');
    var checkbox = elem.parentNode.getElementsByClassName('step-checkbox');
    if (isExpanded === 'false') {
        elem.setAttribute('data-isexpanded', 'true');
        $(checkbox).fadeIn();
    } else {
        elem.setAttribute('data-isexpanded', 'false');
        $(checkbox).fadeOut();
    }
}


function expColTestResultTable(spnHead) {
    var stepDetNode = spnHead.parentNode.getElementsByTagName("table")[0];
    if (spnHead.className == 'expand-img') {
        $(stepDetNode).slideDown("fast");
        spnHead.className = 'collapse-img';
    }
    else {
        $(stepDetNode).slideUp("fast");
        spnHead.className = 'expand-img';
    }
}
function showHideNodes(cb){
    if (cb.checked) {
        $('#sidebar-main li.' + cb.name).slideDown();
    }
    else {
        $('#sidebar-main li.' + cb.name).slideUp();
    }
}

function scrollToVideo(elem){
    var elmFlow = findAncestor(elem,"flow");
    if(elmFlow.getElementsByClassName('i-video-anch').length != 0){
        window.scrollTo(0,document.body.scrollHeight);
    }else{
        alert('No video recording found for this test!!!');
    }
}

function findAncestor (el, cls) {
    while ((el = el.parentElement) && !el.classList.contains(cls));
    return el;
}

