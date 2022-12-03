/**
 * register页面功能:
 * 1.校验输入值
 * 2.post请求
 * 3.根据返回值跳转
 */



/**
 * 发送请求
 */
let app2 = new Vue({
    el: "#loginDiv",
    data: {
        urlBase: "http://localhost:8080/",
        user:{

            serialVersionUID: null,
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
        /**
         * 注册点击请求
         */
        async click_register(){
            console.log(this.urlBase);
            const  res = await register_post(this.urlBase + "user/register",this.user);
            let _this = this;
            console.log(res);
            if(res.data.code === 1){
                this.$message({
                    type:"success",
                    showClose: true,
                    message:'登录成功，即将跳转到登录页面'
                });

                /**
                 * 注册成功后两秒跳转
                 *
                 */
                setTimeout(()=> {
                   // window.location.href = _this.urlBase + "user/index.html";

                    window.open(this.urlBase+'front/pages/login.html')
                }, 2000);

            }else{
                this.$message.error("注册失败！");
            }
        },
    }
});





