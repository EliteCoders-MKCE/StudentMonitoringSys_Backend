(this["webpackJsonpstudent-monitoring"]=this["webpackJsonpstudent-monitoring"]||[]).push([[6],{478:function(t,e,n){"use strict";n.r(e);var r=n(70),c=n(73),o=n(81),s=n(80),a=n(5),i=n.n(a),l=n(1),u=n(9),d=n(0);function f(t){return Object(d.jsx)("div",{style:{marginTop:"5px"},children:Object(d.jsxs)("div",{className:"row sgs-center log-row dyn-margintop-log-row",children:[Object(d.jsx)("div",{className:"col-md-4",children:t.data.attendance_id}),Object(d.jsx)("div",{className:"col-md-4",children:t.data.date_time}),Object(d.jsxs)("div",{className:"col-md-4",children:[Object(d.jsxs)("button",{className:"btn btn-primary bg-blue-500",onClick:function(){return function(t){var e=sessionStorage.getItem("classGroup").toLowerCase().replace(/-/g,"_");window.open(u.ip+"api/attn/download?class_group="+e+"&file_id="+t)}(t.data.date_time)},style:{padding:"4px"},children:[Object(d.jsx)("i",{className:"material-icons small",children:"cloud_download"})," Download"]}),Object(d.jsxs)("button",{className:"btn btn-danger bg-red-500 give-topmargin",onClick:function(){return function(t){var e=sessionStorage.getItem("classGroup").toLowerCase().replace(/-/g,"_");i.a.get(u.ip+"api/attn/delete-log?class_group="+e+"&file_id="+t,(function(t,e){"success"===e&&(i.a.toaster(t,"","success"),setTimeout((function(){window.location.reload()}),800))})).fail((function(){i.a.toaster("Request Failed...","","danger")}))}(t.data.date_time)},style:{padding:"5px",marginLeft:"10px"},children:[Object(d.jsx)("i",{className:"material-icons small",children:"delete"})," Delete"]})]})]})})}var p=function(t){Object(o.a)(n,t);var e=Object(s.a)(n);function n(){var t;Object(r.a)(this,n),(t=e.call(this)).state={data:null};var c=sessionStorage.getItem("classGroup").toLowerCase().replace(/-/g,"_");return i.a.get(u.ip+"api/attn/get-log?class_group="+c,(function(e,n){"success"===n&&t.setState({data:e})})),t}return Object(c.a)(n,[{key:"render",value:function(){return this.state.data?Object(d.jsxs)("div",{className:"dashboard-cont",children:[Object(d.jsx)("div",{className:"sgs-center",children:Object(d.jsx)("span",{className:"text-xl",children:"ATTENDANCE LOGS"})}),Object(d.jsx)("br",{}),Object(d.jsx)("div",{style:{marginTop:"5px"},className:"hide-onphone",children:Object(d.jsxs)("div",{className:"row sgs-center",style:{color:"lightsalmon",padding:"10px",borderRadius:"5px",border:"1px dotted #4723D9"},children:[Object(d.jsx)("div",{className:"col-md-4",children:"TITLE"}),Object(d.jsx)("div",{className:"col-md-4",children:"DATE-TIME"}),Object(d.jsx)("div",{className:"col-md-4",children:"FILE"})]})}),Object(d.jsx)("div",{children:this.state.data.map((function(t,e){return Object(d.jsx)(f,{data:t},e)}))})]}):Object(d.jsx)(d.Fragment,{children:Object(d.jsx)("div",{class:"text-center",children:Object(d.jsx)("div",{class:"spinner-border",role:"status",children:Object(d.jsx)("span",{class:"sr-only",children:"Loading..."})})})})}}]),n}(l.Component);e.default=p},70:function(t,e,n){"use strict";function r(t,e){if(!(t instanceof e))throw new TypeError("Cannot call a class as a function")}n.d(e,"a",(function(){return r}))},73:function(t,e,n){"use strict";function r(t,e){for(var n=0;n<e.length;n++){var r=e[n];r.enumerable=r.enumerable||!1,r.configurable=!0,"value"in r&&(r.writable=!0),Object.defineProperty(t,r.key,r)}}function c(t,e,n){return e&&r(t.prototype,e),n&&r(t,n),t}n.d(e,"a",(function(){return c}))},80:function(t,e,n){"use strict";n.d(e,"a",(function(){return a}));var r=n(89);function c(t){return(c="function"===typeof Symbol&&"symbol"===typeof Symbol.iterator?function(t){return typeof t}:function(t){return t&&"function"===typeof Symbol&&t.constructor===Symbol&&t!==Symbol.prototype?"symbol":typeof t})(t)}var o=n(90);function s(t,e){return!e||"object"!==c(e)&&"function"!==typeof e?Object(o.a)(t):e}function a(t){var e=function(){if("undefined"===typeof Reflect||!Reflect.construct)return!1;if(Reflect.construct.sham)return!1;if("function"===typeof Proxy)return!0;try{return Date.prototype.toString.call(Reflect.construct(Date,[],(function(){}))),!0}catch(t){return!1}}();return function(){var n,c=Object(r.a)(t);if(e){var o=Object(r.a)(this).constructor;n=Reflect.construct(c,arguments,o)}else n=c.apply(this,arguments);return s(this,n)}}},81:function(t,e,n){"use strict";function r(t,e){return(r=Object.setPrototypeOf||function(t,e){return t.__proto__=e,t})(t,e)}function c(t,e){if("function"!==typeof e&&null!==e)throw new TypeError("Super expression must either be null or a function");t.prototype=Object.create(e&&e.prototype,{constructor:{value:t,writable:!0,configurable:!0}}),e&&r(t,e)}n.d(e,"a",(function(){return c}))},89:function(t,e,n){"use strict";function r(t){return(r=Object.setPrototypeOf?Object.getPrototypeOf:function(t){return t.__proto__||Object.getPrototypeOf(t)})(t)}n.d(e,"a",(function(){return r}))},90:function(t,e,n){"use strict";function r(t){if(void 0===t)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return t}n.d(e,"a",(function(){return r}))}}]);
//# sourceMappingURL=6.3755341c.chunk.js.map