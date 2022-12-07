/**
    login页面功能
        1.输入用户名、用户密码并校验
        2.校验后请求
        3.根据返回值跳转
 */

let but = document.querySelector("#loginDiv");
let app = new Vue({
    el: "#loginDiv",

    data: {
        urlBase: "http://172.23.161.242:8080/",
        user:{

            id: null,
            userAccountId: null,
            username: null,
            password: null,
            userEmail: null,
            userSex: null,
            userClass: null,
            userStatement: null,
            userStatus:null,
            createTime: null,
            updateTime: null
        },
    },
    methods: {
        //http://192.168.142.1:8080/user/login
        async click_login() {
            console.log(this.user.username)
            console.log(this.user.password)
            /**
             * 输入值安全性校验
             * 用户名以及密码
             * @type {boolean}
             */
             this.user.username.trim()
            //校验控制变量
            let judge_flag = true;
            //用户名
            let reg = /^[a-zA-Z_][a-zA-Z_-~0-9]{5,9}$/;
            judge_flag = judge_flag && reg.test(this.user.username)
            //密码
            let reg1 = /^[a-zA-Z0-9_-~\@#&*]{6,12}$/;
            let judge_flag2 =  reg1.test(this.user.password)
            //判断校验结果
            if(!judge_flag || !judge_flag2) {
                this.$message.error("用户名或密码格式不符合法！");
                return;
            }

            /**
             * 发送登录请求
             * @type {Window}
             */
            //更名外层函数this
            let _this = this;
            console.log(this.user)
            //请求体
            let res = await  post(this.urlBase + "user/login",this);
            console.log("登录响应体：",res)
            console.log("状态码",res.data.code)
            if( res.data.msg === 'error'){
                this.$message.error("用户名或密码错误！");
                return;
            }

            /**
             * 登录成功，跳转页面
             * @type {string}
             */
            this.$message({
                showClose: true,
                message: '登录成功',
                type: 'success'
            })
            this.user = res.data.data
            console.log("get res = ",res.data)
            console.log("用户data,",this.user)
            setTimeout( ()=> {
                // window.location.href = _this.urlBase + "user/index.html";

                window.location.href = this.urlBase+'front/pages/index.html'
            }, 1000);

        }
    }
})