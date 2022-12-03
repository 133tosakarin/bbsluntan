//请求
async function get(url)
{//js不能重载,但是可以根据参数个数判读执行操作
    //所以直接传参即可
    let ares = null;

    if(arguments.length === 1)
    {//普通请求,参数:url
        await axios({
            method: 'get',
            url: url + '?' + "username=" + _this.username + "&" + "password=" + _this.password,
        }).then(res => {
            console.log(res);
            ares = res;
        }, err => {
            console.log(err)
        })
    }
    else
    {//页面展示请求参数:url, page, pageSize, id
        console.log("查询参数",arguments[3])
        await axios({
            method: 'get',

            url: url + "?" + "page=" + arguments[1] + "&pageSize=" + arguments[2] + "&condition=" + arguments[3],
        }).then(res=>{
            console.log(res);
            ares = res;
        },err=>{
            console.log(err);
        })
    }
    return ares;
}
//加密请求
async  function post(url,records)
{
    console.log(url)
    let ares = null;
    console.log(url)
    console.log("显示this.user信息")
    console.log(records)
    console.log(records.user)
    const _this = records
    await axios({
        method: "post",
        url: url,
        data: {
            username:records.user.username,
            password:records.user.password
        }
    }).then(res=>{
        console.log("axios中：",res);
        ares = res;
    },err=>{
        console.log(err);
    })
    console.log("要返回时：",ares)
     return  ares;
}//加密请求
async function register_post(url,_this)
{
    let ares = null;
    await axios({
        method: "post",
        url: url,
        data: {
            username: _this.username,
            password: _this.password
        }
    }).then(res=>{
        console.log(res);
        ares = res;

    },err=>{
        console.log(err);
    })
    return ares;
}
//更新
async function put(url, record)
{
    let ares = null;
    await axios({
        method: 'put',
        url: url,
        data: record.user,
    }).then(res=>{
        console.log(res);
        ares = res;
    },err=>{
        console.log(err)
    })
    return ares;
}
//删除
function del(url)
{
    let ares = res;
    axios({
        method: 'delete',
        url: url,
    }).then(res=>{
        console.log(res);
        ares = res;
    },err=>{
        console.log(err)
    })
    return res;
}
//展示主页面
function showMyTopic(url, page, pageSize)
{
    axios({
        url: url + "?page=" + page + "&pageSize=" + pageSize,
        method: 'get',
    }).then(res => {
        _this.dataList = res.dataList;
    }, err => {
        console.log(err)
    })

}

//跳转index.html后获取主信息数组，将本地数组赋值，然后v-model、v-for绑定
function showList(url)
{
    let reply;
    axios({
        url: url,
        method: 'get',
    }).then(res => {
        //获取结果
        reply = res;
    }, err => {
        console.log(err)
    });
    _this.dataList = reply.dataList;
}

//register页面校验成功后的请求注册并接收注册结果
function register(url)
{
    axios({
        url: url,
        method: 'get',
    }).then(res => {
        return res.code === 1;
    }, err => {
        console.log(err)
    });
}

async function get_user(){
    let ares;
    await axios({
        method: 'get',
        url: "http://localhost:8080/user/get",
    }).then(res => {
        console.log(res);
        ares = res;
    }, err => {
        console.log(err);
        ares = err;
    });
    return ares;
}

/*页面data模板
let app = new Vue({
    data: {
        serialVersionUID: null,
        id: null,
        userAccountId: null,
        userName: null,
        userPassword: null,
        userEmail: null,
        userSex: null,
        userClass: null,
        userStatement: null,
        createTime: null,
        updateTime: null,
    }
})
*/

async function getTopicById(url){
    console.log(url)
    let res = null
    await axios({
        method:'get',
        url:url
    }).then(resp=>{
        console.log("get topic:",res)
        res = resp
    })
    return res
}

async  function post_comm(url,records){
    console.log( "url = ",url," records = ",records)
    let res
    await axios({
        method:'post',
        data:records,
        url:url
    }).then(resp=>{
        console.log("resp = ",resp)
        res = resp
    })

    return res
}


