webpackJsonp([7],{"NEX+":function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r=a("woOf"),i=a.n(r),l=a("BO1k"),s=a.n(l),o=a("ocgh"),n=a("vLgD");function c(e){return Object(n.a)({url:"/dapp/getList.do",method:"get",params:e})}function p(e){return Object(n.a)({url:"/dapp/update.do",method:"post",params:e})}var d=a("0xDb"),u=[{key:1,value:"Common",label:"通用"},{key:2,value:"Business",label:"商业"},{key:3,value:"Social",label:"社交"},{key:4,value:"Education",label:"教育"},{key:5,value:"Entertainment",label:"娱乐"},{key:6,value:"News",label:"新闻"},{key:7,value:"Life",label:"生活"},{key:8,value:"Utilities",label:"工具"},{key:9,value:"Games",label:"游戏"}],m={data:function(){return{list:null,developers:null,total:0,listQuery:{name:"",category:null,creatorId:null,page:1,limit:10,sort:"-createTime"},temp:{dappId:void 0,name:"",tags:"",description:"",secrets:"",creatorId:"",icon:"",link:"",type:0,unlockDelegates:0},tempSecret:{dappId:void 0,secret:void 0,secondSecret:void 0},tempTransfer:{dappId:void 0,secret:void 0,secondSecret:void 0,currency:void 0,amount:0},dialogFormVisible:!1,secretFormVisible:!1,transferFormVisible:!1,dialogStatus:"",textMap:{update:"编辑dapp",create:"添加dapp"},categoryMap:u,statusOptions:[{key:0,value:"已创建"},{key:1,value:"已注册"},{key:2,value:"已安装"},{key:3,value:"已启动"},{key:4,value:"已关闭"},{key:5,value:"已移除"}],fileList:[],currentRow:null,listLoading:!0}},computed:{fileUploadUrl:function(){return n.a.defaults.baseURL+"/dapp/upload.do"},dappUploadUrl:function(){return n.a.defaults.baseURL+"/dapp/uploadDapp.do"}},filters:{statusFilter:function(e){return{0:"已创建",1:"已注册",2:"已安装",3:"已启动",4:"已停止",5:"已卸载"}[e]},formatTime:function(e){return Object(d.a)(e)},parseCategory:function(e){for(var t=0;t<u.length;t++)if(u[t].key===e)return u[t].label;return""}},created:function(){this.fetchData()},methods:{handleRemove:function(e,t){},handlePreview:function(e){},setSelectedDapp:function(e){console.log("setDappId called: ",e),this.currentRow=e},beforeImageUpload:function(e){return"image/jpeg"===e.type||"image/gif"===e.type||"image/png"===e.type?!(e.size>=102400)||(this.$message.error("上传文件大小不能超过 100K!"),!1):(console.log("filetype:",e.type),this.$message.error("上传文件只能是图片格式!"),!1)},uploadImageSuccess:function(e,t,a){2e4===e.code?(this.temp.icon=e.data,console.dir(this.temp)):this.$message.error("服务器保存ICON文件失败: "+e.message)},updateImageSuccess:function(e,t,a){var r=this;2e4===e.code?p({dappId:this.currentRow.dappId,icon:e.data}).then(function(){r.currentRow.icon=e.data;var t=!0,a=!1,i=void 0;try{for(var l,o=s()(r.list);!(t=(l=o.next()).done);t=!0){var n=l.value;if(n.dappId===r.currentRow.dappId){n.icon=e.data,r.$notify({title:"成功",message:"更新成功",type:"success",duration:2e3});break}}}catch(e){a=!0,i=e}finally{try{!t&&o.return&&o.return()}finally{if(a)throw i}}}):this.$message.error("服务器保存ICON文件失败: "+e.message)},updateDappLinkSuccess:function(e,t,a){var r=this;2e4===e.code?p({dappId:this.currentRow.dappId,link:e.data}).then(function(){r.currentRow.icon=e.data;var t=!0,a=!1,i=void 0;try{for(var l,o=s()(r.list);!(t=(l=o.next()).done);t=!0){var n=l.value;if(n.dappId===r.currentRow.dappId){var c=r.list.indexOf(n);r.list.splice(c,1,r.currentRow),r.$notify({title:"成功",message:"更新成功",type:"success",duration:2e3});break}}}catch(e){a=!0,i=e}finally{try{!t&&o.return&&o.return()}finally{if(a)throw i}}}):this.$message.error("服务器保存ICON文件失败: "+e.message)},beforeDappUpload:function(e){return console.log("Before dapp upload"),"application/x-zip-compressed"===e.type||"application/zip"===e.type?!(e.size>=1048576)||(this.$message.error("上传文件大小不能超过 1M!"),!1):(console.log("dapp filetype: ",e.type),this.$message.error("上传文件只能是zip格式!"),!1)},uploadDappSuccess:function(e,t,a){2e4===e.code?(this.temp.link=e.data,console.dir(this.temp)):this.$message.error("服务器保存Dapp文件失败: "+e.message)},handleExceed:function(e,t){},beforeRemove:function(e,t){},onBeforeUploadIcon:function(e){var t="image/jpeg"===e.type||"image/gif",a=e.size/100/1024<1;return console.log("size: ",e.size,", file.type: ",e.type),t||this.$message.error("上传文件只能是图片格式!"),a||this.$message.error("上传文件大小不能超过 100KB!"),t&&a},fetchData:function(){var e=this;this.listLoading=!0,c(this.listQuery).then(function(t){e.list=t.data.list,e.total=t.data.page.countTotal,e.listLoading=!1}).catch(function(e){console.trace(e)}),Object(o.b)({userType:1,limit:1e4}).then(function(t){e.developers=t.data.list}).catch(function(e){console.trace(e)})},getList:function(){var e=this;this.listLoading=!0,c(this.listQuery).then(function(t){e.list=t.data.list,e.total=t.data.page.countTotal,setTimeout(function(){e.listLoading=!1},200)})},handleFilter:function(){this.listQuery.page=1,this.getList()},handleSizeChange:function(e){this.listQuery.limit=e,this.getList()},handleCurrentChange:function(e){this.listQuery.page=e,this.getList()},resetTemp:function(){this.temp={dappId:void 0,name:"",state:0,icon:"",link:"",secrets:"flame bottom dragon rely endorse garage supply urge turtle team demand put,thrive veteran child enforce puzzle buzz valley crew genuine basket start top,black tool gift useless bring nothing huge vendor asset mix chimney weird,ribbon crumble loud chief turn maid neglect move day churn share fabric,scan prevent agent close human pair aerobic sad forest wave toe dust",delegates:"db18d5799944030f76b6ce0879b1ca4b0c2c1cee51f53ce9b43f78259950c2fd,590e28d2964b0aa4d7c7b98faee4676d467606c6761f7f41f99c52bb4813b5e4,bfe511158d674c3a1e21111223a49770bee93611d998e88a5d2ea3145de2b68b,7bbf62931cf3c596591a580212631aff51d6bc0577c54769953caadb23f6ab00,452df9213aedb3b9fed6db3e2ea9f49d3db226e2dac01828bc3dcd73b7a953b4"}},resetTempSecret:function(){this.tempSecret={dappId:void 0,secret:"",secondSecret:""}},handleRegister:function(e){var t=this;this.tempSecret={dappId:e,secret:"someone manual strong movie roof episode eight spatial brown soldier soup motor",secondSecret:""},this.secretFormVisible=!0,this.$nextTick(function(){t.$refs.secretForm.clearValidate()})},handleTransferToDapp:function(e){var t=this;this.tempTransfer={dappId:e,secret:"someone manual strong movie roof episode eight spatial brown soldier soup motor",secondSecret:""},this.transferFormVisible=!0,this.$nextTick(function(){t.$refs.transferForm.clearValidate()})},handleCreate:function(){var e=this;this.resetTemp(),this.dialogStatus="create",this.dialogFormVisible=!0,this.$nextTick(function(){e.$refs.dataForm.clearValidate()})},createData:function(){var e=this;this.$refs.dataForm.validate(function(t){var a;t&&(a=e.temp,Object(n.a)({url:"/dapp/create.do",method:"post",params:a})).then(function(t){e.temp.dappId=t.data,e.temp.createTime=(new Date).getTime(),e.list.unshift(e.temp),e.dialogFormVisible=!1,e.$notify({title:"成功",message:"创建成功",type:"success",duration:2e3})})})},handleModifyState:function(e,t){var a,r=this;(a={dappId:e,state:t},Object(n.a)({url:"/dapp/updateState.do",method:"post",params:a})).then(function(){var a=!0,i=!1,l=void 0;try{for(var o,n=s()(r.list);!(a=(o=n.next()).done);a=!0){var c=o.value;if(c.dappId===e){c.state=t;break}}}catch(e){i=!0,l=e}finally{try{!a&&n.return&&n.return()}finally{if(i)throw l}}r.$notify({title:"成功",message:"更新成功",type:"success",duration:2e3})})},handleUpdate:function(e){var t=this;this.temp=i()({},e),this.temp.password="",this.dialogStatus="update",this.dialogFormVisible=!0,this.$nextTick(function(){t.$refs.dataForm.clearValidate()})},updateData:function(){var e=this;this.$refs.dataForm.validate(function(t){if(t){var a=i()({},e.temp);a.createTime=null,a.updateTime=null,p(a).then(function(){var t=!0,a=!1,r=void 0;try{for(var i,l=s()(e.list);!(t=(i=l.next()).done);t=!0){var o=i.value;if(o.dappId===e.temp.dappId){var n=e.list.indexOf(o);e.list.splice(n,1,e.temp);break}}}catch(e){a=!0,r=e}finally{try{!t&&l.return&&l.return()}finally{if(a)throw r}}e.dialogFormVisible=!1,e.$notify({title:"成功",message:"更新成功",type:"success",duration:2e3})})}})},registerDapp:function(){var e=this;this.$refs.secretForm.validate(function(t){if(t){var a=i()({},e.tempSecret);console.log(" ==== register ==== ",a),(r=a,Object(n.a)({url:"/dapp/register.do",method:"post",params:r})).then(function(){e.temp.state=1;var t=!0,a=!1,r=void 0;try{for(var i,l=s()(e.list);!(t=(i=l.next()).done);t=!0){var o=i.value;if(o.dappId===e.temp.dappId){var n=e.list.indexOf(o);e.list.splice(n,1,e.temp);break}}}catch(e){a=!0,r=e}finally{try{!t&&l.return&&l.return()}finally{if(a)throw r}}e.secretFormVisible=!1,e.$notify({title:"成功",message:"注册成功",type:"success",duration:2e3})})}var r})},transferToDapp:function(){var e=this;this.$refs.transferForm.validate(function(t){if(t){var a=i()({},e.tempTransfer);console.log(" ==== register ==== ",a),(r=a,Object(n.a)({url:"/dapp/transferToDapp.do",method:"post",params:r})).then(function(){e.temp.state=1;var t=!0,a=!1,r=void 0;try{for(var i,l=s()(e.list);!(t=(i=l.next()).done);t=!0){var o=i.value;if(o.dappId===e.temp.dappId){var n=e.list.indexOf(o);e.list.splice(n,1,e.temp);break}}}catch(e){a=!0,r=e}finally{try{!t&&l.return&&l.return()}finally{if(a)throw r}}e.transferFormVisible=!1,e.$notify({title:"成功",message:"转账成功",type:"success",duration:2e3})})}var r})},handleDelete:function(e){this.$notify({title:"成功",message:"删除成功",type:"success",duration:2e3});var t=this.list.indexOf(e);this.list.splice(t,1)}}},f={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("div",{staticClass:"filter-container"},[a("el-select",{staticClass:"filter-item",attrs:{clearable:"",placeholder:"应用类型"},model:{value:e.listQuery.category,callback:function(t){e.$set(e.listQuery,"category",t)},expression:"listQuery.category"}},e._l(e.categoryMap,function(e){return a("el-option",{key:e.key,attrs:{label:e.label,value:e.key}})})),e._v(" "),a("el-input",{staticStyle:{width:"100px"},attrs:{placeholder:"应用名称"},model:{value:e.listQuery.name,callback:function(t){e.$set(e.listQuery,"name",t)},expression:"listQuery.name"}}),e._v(" "),a("el-select",{staticClass:"filter-item",attrs:{clearable:"",placeholder:"开发者"},model:{value:e.listQuery.creatorId,callback:function(t){e.$set(e.listQuery,"creatorId",t)},expression:"listQuery.creatorId"}},e._l(e.developers,function(e){return a("el-option",{key:e.memberId,attrs:{label:e.account,value:e.memberId}})})),e._v(" "),a("el-button",{staticClass:"filter-item",attrs:{type:"primary",icon:"el-icon-search"},on:{click:e.handleFilter}},[e._v("搜索")]),e._v(" "),a("el-button",{staticClass:"filter-item",staticStyle:{"margin-left":"10px"},attrs:{type:"primary",icon:"el-icon-add"},on:{click:e.handleCreate}},[e._v("添加dapp")])],1),e._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading.body",value:e.listLoading,expression:"listLoading",modifiers:{body:!0}}],attrs:{data:e.list,"element-loading-text":"Loading",border:"",fit:"","highlight-current-row":""}},[a("el-table-column",{attrs:{label:"名称",width:"110",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.name))])]}}])}),e._v(" "),a("el-table-column",{attrs:{"class-name":"status-col",label:"类型",width:"110",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n        "+e._s(e._f("parseCategory")(t.row.category))+"\n      ")]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"图标",width:"110",align:"center"},scopedSlots:e._u([{key:"default",fn:function(e){return[a("img",{staticStyle:{width:"50px",height:"50px"},attrs:{src:e.row.icon}})]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"所有者",width:"110",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n        "+e._s(t.row.owner)+"\n      ")]}}])}),e._v(" "),a("el-table-column",{attrs:{"class-name":"status-col",label:"状态",width:"110",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n        "+e._s(e._f("statusFilter")(t.row.state))+"\n      ")]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"创建日期",width:"160"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(e._f("formatTime")(t.row.createTime)))])]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"action",label:"操作",width:"480"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[a("el-button",{staticStyle:{float:"left"},attrs:{type:"primary",size:"mini"},on:{click:function(a){e.handleUpdate(t.row)}}},[e._v("编辑")]),e._v(" "),a("el-upload",{staticStyle:{float:"left","margin-left":"8px"},attrs:{accept:"image/jpeg,image/gif,image/png",action:e.fileUploadUrl,"show-file-list":!1,"before-upload":e.beforeImageUpload,name:"files","on-success":e.updateImageSuccess,multiple:"",limit:1,"on-exceed":e.handleExceed,"file-list":e.fileList}},[a("el-button",{attrs:{size:"small",type:"primary"},on:{click:function(a){e.setSelectedDapp(t.row)}}},[e._v("更换图标")])],1),e._v(" "),a("el-upload",{staticStyle:{float:"left","margin-left":"8px"},attrs:{accept:"application/x-zip-compressed,application/zip",action:e.dappUploadUrl,"before-upload":e.beforeDappUpload,name:"files","on-success":e.updateDappLinkSuccess,multiple:"",limit:1}},[a("el-button",{attrs:{size:"small",type:"primary"},on:{click:function(a){e.setSelectedDapp(t.row)}}},[e._v("更换代码")])],1),e._v(" "),"0"==t.row.state?a("el-button",{attrs:{size:"mini"},on:{click:function(a){e.handleRegister(t.row.dappId)}}},[e._v("注册")]):e._e(),e._v(" "),"1"==t.row.state||"5"==t.row.state?a("el-button",{attrs:{size:"mini"},on:{click:function(a){e.handleModifyState(t.row.dappId,"2")}}},[e._v("安装")]):e._e(),e._v(" "),"2"==t.row.state||"4"==t.row.state?a("el-button",{attrs:{size:"mini"},on:{click:function(a){e.handleModifyState(t.row.dappId,"3")}}},[e._v("启动")]):e._e(),e._v(" "),"3"==t.row.state?a("el-button",{attrs:{size:"mini"},on:{click:function(a){e.handleModifyState(t.row.dappId,"4")}}},[e._v("停止")]):e._e(),e._v(" "),"3"==t.row.state?a("el-button",{attrs:{size:"mini"},on:{click:function(a){e.handleTransferToDapp(t.row.dappId)}}},[e._v("转账")]):e._e(),e._v(" "),"3"==t.row.state||"2"==t.row.state||"4"==t.row.state?a("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(a){e.handleModifyState(t.row.dappId,"5")}}},[e._v("卸载")]):e._e()],1)]}}])})],1),e._v(" "),a("div",{staticClass:"pagination-container"},[a("el-pagination",{attrs:{background:"","current-page":e.listQuery.page,"page-sizes":[10,20,30,50],"page-size":e.listQuery.limit,layout:"total, sizes, prev, pager, next, jumper",total:e.total},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],1),e._v(" "),a("el-dialog",{attrs:{title:"请输入注册应用的主密码和二级密码",visible:e.secretFormVisible},on:{"update:visible":function(t){e.secretFormVisible=t}}},[a("el-form",{ref:"secretForm",staticStyle:{width:"400px","margin-left":"50px"},attrs:{model:e.tempSecret,"label-position":"left","label-width":"70px"}},[a("el-form-item",{attrs:{label:"主密码",prop:"secret"}},[a("el-input",{attrs:{required:""},model:{value:e.tempSecret.secret,callback:function(t){e.$set(e.tempSecret,"secret",t)},expression:"tempSecret.secret"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"二级密码",prop:"secondSecret"}},[a("el-input",{model:{value:e.tempSecret.secondSecret,callback:function(t){e.$set(e.tempSecret,"secondSecret",t)},expression:"tempSecret.secondSecret"}})],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.secretFormVisible=!1}}},[e._v("取消")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:e.registerDapp}},[e._v("注册")])],1)],1),e._v(" "),a("el-dialog",{attrs:{title:"转账",visible:e.transferFormVisible},on:{"update:visible":function(t){e.transferFormVisible=t}}},[a("el-form",{ref:"transferForm",staticStyle:{width:"400px","margin-left":"50px"},attrs:{model:e.tempTransfer,"label-position":"left","label-width":"70px"}},[a("el-form-item",{attrs:{label:"主密码",prop:"secret"}},[a("el-input",{attrs:{required:""},model:{value:e.tempTransfer.secret,callback:function(t){e.$set(e.tempTransfer,"secret",t)},expression:"tempTransfer.secret"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"二级密码",prop:"secondSecret"}},[a("el-input",{model:{value:e.tempTransfer.secondSecret,callback:function(t){e.$set(e.tempTransfer,"secondSecret",t)},expression:"tempTransfer.secondSecret"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"currency",prop:"currency"}},[a("el-input",{attrs:{required:""},model:{value:e.tempTransfer.currency,callback:function(t){e.$set(e.tempTransfer,"currency",t)},expression:"tempTransfer.currency"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"金额",prop:"amount"}},[a("el-input",{attrs:{required:""},model:{value:e.tempTransfer.amount,callback:function(t){e.$set(e.tempTransfer,"amount",t)},expression:"tempTransfer.amount"}})],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.transferFormVisible=!1}}},[e._v("取消")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:e.transferToDapp}},[e._v("转账")])],1)],1),e._v(" "),a("el-dialog",{attrs:{title:e.textMap[e.dialogStatus],visible:e.dialogFormVisible},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[a("el-form",{ref:"dataForm",staticStyle:{width:"400px","margin-left":"50px"},attrs:{model:e.temp,"label-position":"left","label-width":"70px"}},[a("el-form-item",{attrs:{label:"名称",prop:"name"}},[a("el-input",{attrs:{required:""},model:{value:e.temp.name,callback:function(t){e.$set(e.temp,"name",t)},expression:"temp.name"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"类别",prop:"category"}},[a("el-select",{staticClass:"filter-item",attrs:{placeholder:"Please select"},model:{value:e.temp.category,callback:function(t){e.$set(e.temp,"category",t)},expression:"temp.category"}},e._l(e.categoryMap,function(e){return a("el-option",{key:e.key,attrs:{label:e.label,value:e.key}})}))],1),e._v(" "),a("el-form-item",{attrs:{label:"标签",prop:"tags"}},[a("el-input",{model:{value:e.temp.tags,callback:function(t){e.$set(e.temp,"tags",t)},expression:"temp.tags"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"描述",prop:"description"}},[a("el-input",{model:{value:e.temp.description,callback:function(t){e.$set(e.temp,"description",t)},expression:"temp.description"}})],1),e._v(" "),"create"==e.dialogStatus?a("el-form-item",{attrs:{label:"图标",prop:"icon"}},[a("el-upload",{staticClass:"upload-demo",attrs:{accept:"image/jpeg,image/gif,image/png",action:e.fileUploadUrl,"on-preview":e.handlePreview,"on-remove":e.handleRemove,"before-remove":e.beforeRemove,"before-upload":e.beforeImageUpload,name:"files","on-success":e.uploadImageSuccess,multiple:"",limit:1,"list-type":"picture","on-exceed":e.handleExceed,"file-list":e.fileList}},[a("el-button",{attrs:{size:"small",type:"primary"}},[e._v("点击上传")]),e._v(" "),a("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[e._v("只能上传jpg/png文件，且不超过100kb")])],1)],1):e._e(),e._v(" "),"create"==e.dialogStatus?a("el-form-item",{attrs:{label:"代码",prop:"link"}},[a("el-upload",{staticClass:"upload-demo",attrs:{accept:"application/x-zip-compressed",action:e.dappUploadUrl,"before-upload":e.beforeDappUpload,name:"files","on-success":e.uploadDappSuccess,multiple:"",limit:1}},[a("el-button",{attrs:{size:"small",type:"primary"}},[e._v("点击上传")]),e._v(" "),a("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[e._v("只能上传zip文件，且不超过1M")])],1)],1):e._e(),e._v(" "),a("el-form-item",{attrs:{label:"代理人密码",prop:"secrets"}},[a("el-input",{attrs:{type:"textarea",rows:"6"},model:{value:e.temp.secrets,callback:function(t){e.$set(e.temp,"secrets",t)},expression:"temp.secrets"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"代理人公钥",prop:"delegates"}},[a("el-input",{attrs:{type:"textarea",rows:"6"},model:{value:e.temp.delegates,callback:function(t){e.$set(e.temp,"delegates",t)},expression:"temp.delegates"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"解锁要求",prop:"unlockDelegates"}},[a("el-input",{attrs:{type:"number"},model:{value:e.temp.unlockDelegates,callback:function(t){e.$set(e.temp,"unlockDelegates",t)},expression:"temp.unlockDelegates"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"所有者",prop:"ownerId"}},[a("el-select",{staticClass:"filter-item",staticStyle:{width:"200px"},attrs:{clearable:"",placeholder:"所有者"},model:{value:e.temp.creatorId,callback:function(t){e.$set(e.temp,"creatorId",t)},expression:"temp.creatorId"}},e._l(e.developers,function(e){return a("el-option",{key:e.memberId,attrs:{label:e.account,value:e.memberId}})}))],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogFormVisible=!1}}},[e._v("取消")]),e._v(" "),"create"==e.dialogStatus?a("el-button",{attrs:{type:"primary"},on:{click:e.createData}},[e._v("添加")]):a("el-button",{attrs:{type:"primary"},on:{click:e.updateData}},[e._v("更新")])],1)],1)],1)},staticRenderFns:[]},v=a("VU/8")(m,f,!1,null,null,null);t.default=v.exports}});