(this["webpackJsonpstudent-monitoring"]=this["webpackJsonpstudent-monitoring"]||[]).push([[9],{476:function(e,t,n){"use strict";n.r(t);var c=n(70),s=n(73),r=n(81),i=n(80),o=n(1),a=n(5),l=n.n(a),u=n(9),d=n(0),f=sessionStorage.getItem("classGroup").toLowerCase().replace(/-/g,"_"),m=function(e){Object(r.a)(n,e);var t=Object(i.a)(n);function n(e){var s;return Object(c.a)(this,n),(s=t.call(this)).state={meet:null},l.a.get(u.ip+"api/meet/get-meets?class_group="+f,(function(e,t){"success"===t&&s.setState({meet:e})})).fail((function(){l.a.toaster("Request Failed...","","danger")})),s}return Object(s.a)(n,[{key:"render",value:function(){return this.state.meet?Object(d.jsx)(d.Fragment,{children:Object(d.jsx)("div",{className:"dashboard-cont",children:Object(d.jsxs)("div",{className:"row",children:[Object(d.jsx)("div",{className:"col-md-6 col-sm-12 sgs-center",children:Object(d.jsxs)("div",{className:"meet-create-div",children:[Object(d.jsx)("h3",{className:"text-xl",children:"Create New Meet"}),Object(d.jsx)("input",{type:"text",placeholder:"Meet Title",id:"meet-title",className:"modal-input",required:!0}),Object(d.jsx)("br",{}),Object(d.jsx)("br",{}),Object(d.jsx)("textarea",{placeholder:"Meet Description",id:"meet-description",className:"modal-input",required:!0}),Object(d.jsx)("br",{}),Object(d.jsx)("br",{}),Object(d.jsxs)("button",{onClick:function(){return function(){var e=document.getElementById("meet-title").value,t=document.getElementById("meet-description").value;l.a.get(u.ip+"api/meet/create?class_group="+f+"&title="+e+"&desc="+t,(function(e,t){"success"===t&&(l.a.toaster(e,"","success"),setTimeout((function(){window.location.reload()}),800))})).fail((function(){l.a.toaster("Request Failed...","","danger")}))}()},type:"button",id:"create-meet",className:"btn btn-success",children:[Object(d.jsx)("i",{className:"bx bx-plus"}),"Create Meet"]})]})}),Object(d.jsx)("div",{className:"col-md-6 col-sm-12",children:Object(d.jsxs)("div",{className:"meet-create-div",children:[Object(d.jsx)("h3",{className:"text-xl sgs-center",children:"Available Meetings"}),Object(d.jsx)("br",{}),Object(d.jsxs)("div",{className:"row sgs-center",children:[Object(d.jsx)("div",{className:"col-sm-3 ",style:{color:"gray"},children:"Meet Name"}),Object(d.jsx)("div",{className:"col-sm-3 hide-onphone",style:{color:"gray"},children:"Description"}),Object(d.jsx)("div",{className:"col-sm-6"})]}),this.state.meet.map((function(e,t){return Object(d.jsx)(b,{data:e},t)}))]})})]})})}):Object(d.jsx)(d.Fragment,{children:"Loading.."})}}]),n}(o.Component);function b(e){return Object(d.jsx)(d.Fragment,{children:Object(d.jsxs)("div",{className:"row meet-list sgs-center",children:[Object(d.jsx)("div",{className:"col-sm-3",children:e.data.title}),Object(d.jsx)("div",{className:"col-sm-3",children:e.data.desc}),Object(d.jsxs)("div",{className:"col-sm-6",children:[Object(d.jsxs)("button",{onClick:function(){return function(){var t="https://simclair-sms.azurewebsites.net/build?groupId="+e.data.meet_id+"&name="+sessionStorage.getItem("staffName")+" SuperUser&auth=bmkfbvf224vgcbhb7vjsv45vfnnmbd";window.open(t,"_blank").focus()}()},style:{marginRight:"10px"},type:"button",id:"join-meet",className:"btn btn-success",children:[Object(d.jsx)("i",{style:{marginRight:"6px"},className:"material-icons small left",children:"send"}),"Join"]}),Object(d.jsxs)("button",{onClick:function(){return function(){alert("delete meet method on dev");var t=e.data.meet_id;l.a.get(u.ip+"api/meet/delete?class_group="+f+"&meet_id="+t,(function(e,t){"success"===t&&(l.a.toaster(e,"","success"),setTimeout((function(){window.location.reload()}),800))})).fail((function(){l.a.toaster("Request Failed...","","danger")}))}()},type:"button",id:"delete-meet",className:"btn btn-danger",children:[Object(d.jsx)("i",{style:{marginRight:"6px"},className:"material-icons small left",children:"delete"}),"Delete"]})]})]})})}t.default=m},70:function(e,t,n){"use strict";function c(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}n.d(t,"a",(function(){return c}))},73:function(e,t,n){"use strict";function c(e,t){for(var n=0;n<t.length;n++){var c=t[n];c.enumerable=c.enumerable||!1,c.configurable=!0,"value"in c&&(c.writable=!0),Object.defineProperty(e,c.key,c)}}function s(e,t,n){return t&&c(e.prototype,t),n&&c(e,n),e}n.d(t,"a",(function(){return s}))},80:function(e,t,n){"use strict";n.d(t,"a",(function(){return o}));var c=n(89);function s(e){return(s="function"===typeof Symbol&&"symbol"===typeof Symbol.iterator?function(e){return typeof e}:function(e){return e&&"function"===typeof Symbol&&e.constructor===Symbol&&e!==Symbol.prototype?"symbol":typeof e})(e)}var r=n(90);function i(e,t){return!t||"object"!==s(t)&&"function"!==typeof t?Object(r.a)(e):t}function o(e){var t=function(){if("undefined"===typeof Reflect||!Reflect.construct)return!1;if(Reflect.construct.sham)return!1;if("function"===typeof Proxy)return!0;try{return Date.prototype.toString.call(Reflect.construct(Date,[],(function(){}))),!0}catch(e){return!1}}();return function(){var n,s=Object(c.a)(e);if(t){var r=Object(c.a)(this).constructor;n=Reflect.construct(s,arguments,r)}else n=s.apply(this,arguments);return i(this,n)}}},81:function(e,t,n){"use strict";function c(e,t){return(c=Object.setPrototypeOf||function(e,t){return e.__proto__=t,e})(e,t)}function s(e,t){if("function"!==typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function");e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,writable:!0,configurable:!0}}),t&&c(e,t)}n.d(t,"a",(function(){return s}))},89:function(e,t,n){"use strict";function c(e){return(c=Object.setPrototypeOf?Object.getPrototypeOf:function(e){return e.__proto__||Object.getPrototypeOf(e)})(e)}n.d(t,"a",(function(){return c}))},90:function(e,t,n){"use strict";function c(e){if(void 0===e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return e}n.d(t,"a",(function(){return c}))}}]);
//# sourceMappingURL=9.175f1c11.chunk.js.map