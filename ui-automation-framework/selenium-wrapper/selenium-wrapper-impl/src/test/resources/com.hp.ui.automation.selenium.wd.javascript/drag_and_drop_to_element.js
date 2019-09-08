/**
 * Created by azariam on 08/06/2016.
 */
var source = arguments[0], target = arguments[1];
var rect = source.getBoundingClientRect();
var targetRect = target.getBoundingClientRect();
var offsetX = targetRect.left - rect.left;
var offsetY = targetRect.top - rect.top;
var dragPt = {x: rect.left + (rect.width >> 1), y: rect.top + (rect.height >> 1)};
var dropPt = {x: dragPt.x + offsetX, y: dragPt.y + offsetY};
target = document.elementFromPoint(dropPt.x, dropPt.y);

var dataTransfer = {
    dropEffect: '',
    effectAllowed: 'all',
    files: [],
    items: {},
    types: [],
    setData: function (format, data) {
        this.items[format] = data;
        this.types.push(format);
    },
    getData: function (format) {
        return this.items[format];
    },
    clearData: function (format) { }
};

var emit = function (event, target, pt) {
    var evt = document.createEvent('MouseEvent');
    evt.initMouseEvent(event, true, true, window, 0, 0, 0, pt.x, pt.y, false, false, false, false, 0, null);
    evt.dataTransfer = dataTransfer;
    target.dispatchEvent(evt);
};

emit('mousedown', source, dragPt);
emit('mousemove', source, dragPt);
emit('dragstart', source, dragPt);
emit('mousemove', source, dropPt);
emit('dragenter', target, dropPt);
emit('dragover',  target, dropPt);
emit('drop',      target, dropPt);
emit('dragend',   source, dropPt);
emit('mouseup',   source, dropPt);