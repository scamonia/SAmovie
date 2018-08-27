var app = new Vue({
	el:"#general",
	data:{
		bigNum:1234,
		ticketnum:3333,
		schenum:3333,
		avgprice:3333
	},
	//加载时自动执行
	mounted:function(){
        console.log("成功进入mounted")
        this.$nextTick(function () {
            this.generalData()
        })
    },
	methods:{
		generalData:function () {
            var self = this;
            console.log("成功进入generalData")
			fetch('/boxoffice/general',{
				method:'GET',
				headers:{
					'Accept':'application/json',
					'Content-Type':'application/json'
				},
			}).then(function (res) {
					console.log("成功")
				    res.json().then(function(json){
					    console.log(json);
                        self.bigNum = json.boxNum
                        self.ticketnum = json.ticketNum
                        self.schenum = json.scheNum
                        self.avgprice = json.avgPrice
				    })
			}).then(function (json) {
				console.log("失败")
			})
		}
	}
});

var app2 = new Vue({
    el:"#detail",
    data:{
        movieInfos:[]
    },
    mounted:function(){
        console.log("成功进入mounted app2")
        this.$nextTick(function () {
            this.detailData()
        })
    },
    methods:{
        detailData:function () {
            var self = this;
            console.log("成功进入detailData")
            fetch('/boxoffice/detail',{
                method:'GET',
                headers:{
                    'Accept':'application/json',
                    'Content-Type':'application/json;charset=utf-8'
                },
            }).then(function (res) {
                console.log("detail成功")
                res.json().then(function(json){
                    console.log(json);
                    self.movieInfos = json
                })
            }).then(function (json) {
                console.log("失败")
            })
        }
    }
});