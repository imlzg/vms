var VMS = new Object();
VMS.urlBase = '';
VMS.WANT = 1;
VMS.SEEN = 2;

window.addEvent('domready', function(){

    if ($('s')){
        var f = $('s');
        var c = $('c');
        var q = $('q');
        var a = new Hash({0: VMS.urlBase + '/es', 1: VMS.urlBase + '/ss', 2: VMS.urlBase + '/ns'});

        f.set('action', a.get(c.get('value')));
        if (new RegExp('(/[^\\?/]+)/?\\?q=([^&]+)').test(location.href)){
            var ac = VMS.urlBase + RegExp.$1;
            c.options[a.keyOf(ac)].selected = true;
            q.set('value', decodeURIComponent(RegExp.$2).replace(/\+/g, ' '));
            f.set('action', ac);
        }

        f.addEvent('submit', function(){return ! $('q').get('value').test(/^\s*$/);});
        c.addEvent('change', function(){
            f.set('action', a.get(c.get('value')));
        });
    }


    $$('span.add-more').each(function(span){
        span.addEvent('click', function(e){
            new Event(e).stop();
            var parentObject = this.getParent();
            var curObject = parentObject.clone();

            this.dispose();

            var addObject = curObject.getLast();
            var delObject = addObject.getPrevious();

            addObject.addEvent('click', arguments.callee);

            if (delObject.get('class') == 'del-this'){
                delObject.addEvent('click', function(){
                    delthis(curObject);
                });
            }

            curObject.getChildren().each(function(input){
                if ('no' == input.get('class')){
                    input.set('text', parseInt(input.get('text')) + 1);
                }
                if ('input' == input.get('tag')){
                    var names = /(.*\[)(\d+)(\].*)$/.exec(input.get('name'));
                    input.setProperties({
                        'name' : names[1] + (names[2].toInt() + 1) + names[3],
                        'value' : ''
                    });
                }
            });
            curObject.injectAfter(parentObject);
        });
    });

    $$('span.del-this').each(function(span){
        span.addEvent('click', function(e){
            new Event(e).stop();
            var curObject = span.getParent();
            delthis(curObject);
        });
    });


    $$('.hide-other-on-change').each(function(s){
        var willBeHidden = $$('.hide-on-'+s.id+'-change');

        if (s.options[s.options.selectedIndex].value != '0'){
            willBeHidden.each(function(w){
                w.style.display = 'none';
            });
        } else {
            willBeHidden.each(function(w){
                w.style.display = 'inline';
            });
        }
        s.addEvent('change', function(e){
            if(e) e = new Event(e).stop();
            if (s.options[s.options.selectedIndex].value != '0'){
                willBeHidden.each(function(w){
                    w.style.display = 'none';
                });
            } else {
                willBeHidden.each(function(w){
                    w.style.display = 'inline';
                });
           }
        });
    });




    /*    */
});

window.addEvent('resize', function(){
});



function confirmDelete(){
    return confirm('确实要删除吗?');
}
function confirmRec(){
    return confirm('确实要推荐到首页吗?');
}
function confirmUnrec(){
    return confirm('确实要取消推荐吗?');
}



function delthis(curObject){
    var name = /(.*\[)(\d+)(\].*)$/.exec(curObject.getFirst().get('name'))[1];
    var pObject = curObject.getPrevious();
    var pFirst, pName;
    if ($defined(pObject)) {
        pFirst = pObject.getFirst();
        if ($defined(pFirst)) {
            pName = pFirst.get('name');
        }
    }
    var nObject = curObject.getNext();
    var nFirst, nName;
    if ($defined(nObject)) {
        nFirst = nObject.getFirst();
        if ($defined(nFirst)) {
            nName = nFirst.get('name');
        }
    }

    if (($defined(pName) && name == /(.*\[)(\d+)(\].*)$/.exec(pName)[1]) ||
        ($defined(nName) && name == /(.*\[)(\d+)(\].*)$/.exec(nName)[1])) {
        curObject.getChildren().each(function(input){
            if ('input' == input.get('tag')) {
                input.setProperties({'name' : '', 'value' : ''});
            }
        });
//        curObject.dispose();
        curObject.destroy();

        if (! $defined(nName) || name != /(.*\[)(\d+)(\].*)$/.exec(nName)[1]) {
            var addObject = curObject.getLast();
            if ($defined(addObject) && addObject.get('class') == 'add-more') {
                addObject.injectAfter(pObject.getLast());
            }
        }
    }
}


function vv(v){
    return (v * 10).round(1);
}

function vote(movieId, anonym){
    var v = $('vote');
    var r = v.getParent();
    var rs = r.get('id').match(/_([\d.]*)_([\d.]*)_(\d*)$/);

    var eht = rs[1].toFloat();
    var rvl = rs[2];
    var ers = rs[3].toInt();

    var eud = vv(eht);
    if (eud == 10){
        $('vote_u').set('text', 1);
        $('vote_d').set('class', 'u');
        $('vote_d').set('text', 0);
    }else{
        eud = (eud + '').split('.');
        $('vote_u').set('text', eud[0]);
        $('vote_d').set('class', 'd');
        $('vote_d').set('text', '.' + $pick(eud[1], 0));
    }

    var rt = new Rating({
        scale: 10,
        callbacks: {
            onMousemove : function(el){
                var stars = el.getParent();
                $('vote_t').set('text', ((1 + stars.bg.getStyle('left').toFloat()/stars.w/stars.scale) * 10).round(1));
            },
            onMouseleave: function(el){
                $('vote_t').set('text', '');
            }
        }
    });

    var sm;
    if (anonym){
        rt.inject(v, {
            value: eht,
            mask: true,
            callbacks: {
                onClick: function(el){
                     loginDialog(VMS.urlBase + '/movie/' + movieId);
                }
            }
        });

        sm = '<span>您<a href="' + VMS.urlBase + '/signup" id="login-to-vote">登录</a>后可以评分哦！</span>';
        if (ers > 0){
            sm = '<span>已有 ' + ers + ' 人评分！</span> ' + sm;
        }
        $('vote_s').set('html', sm);
        $('login-to-vote').addEvent('click', function(e){
            if(e) e = new Event(e).stop();
            loginDialog(VMS.urlBase + '/movie/' + movieId);
        });

    }else{
        rt.inject(v, {
            url: VMS.urlBase + '/vote/' + movieId + '_',
            value: eht,
            requests: {
                onSuccess: function(txt){
                    var ts = txt.split('\*');
                    r.setProperty('id', 'vote_' + ts[0] + '_' + ts[1] + '_' + ts[2]);
                    vote(movieId, anonym);

                    // update collect
                    var c = $('collect');
                    if (c && c.get('class').match(/_(\d*)$/)[1] != VMS.SEEN){
                        c.setProperty('class', 'collect_' + VMS.SEEN);
                        gather(movieId, anonym);
                    }

                    //tagDialog(movieId);
                }
            }
        });

        sm = $('vote_s');
        if (rvl > 0){
            sm.set('html', '<span>我的评分: ' + vv(rvl) + '</span>');
        }else{
            sm.set('html', '<span>轻轻地移动您的鼠标就可以评分哦!</span>');
        }
        if (ers > 0){
            sm.grab(new Element('span', {text: '已有 ' + ers + ' 人评分！'}), 'top');
        }

    }
}

function gather(movieId, anonym){
    if (anonym){
        $$($('collect_want'), $('collect_seen')).each(function(el){
            el.addEvent('click', function(){
                loginDialog(VMS.urlBase + '/movie/' + movieId);
            });
        });
    }else{
        var i = $('collect');
        var rs = i.get('class').match(/_(\d*)$/);
        var ct = rs[1];

        if (ct != 0){
            var h = '<span class="title">我看过这部影片</span><span id="collect_want" class="btn1">想看</span>';
            if (ct == VMS.WANT){
                h = '<span class="title">我想看这部影片</span><span id="collect_seen" class="btn1">看过</span>';
            }
            $('collect').set('html', h + ' ·<a id="collect_delete" href="' + VMS.urlBase + '/deletecollect/' + movieId + '" onclick="return confirmDelete();" class="btn3">删除</a>');
        }
        if ($('collect_want')) $('collect_want').addEvent('click', function(){collect(movieId, VMS.WANT);});
        if ($('collect_seen')) $('collect_seen').addEvent('click', function(){collect(movieId, VMS.SEEN);});
    }
}
function collect(movieId, type){
    if (movieId) {
        var req = new Request({
            url: VMS.urlBase + '/collect/' + movieId + '_' + type,
            async: true,
            onComplete:  $empty,
            onSuccess:   function(txt){
                $('collect').setProperty('class', 'collect_' + txt);
                gather(movieId, false);

                var h = '我看过这部影片';
                if (type == VMS.WANT){
                    h = '我想看这部影片';
                }
                tagDialog(movieId, '<h2>' + h + ', 顺便做个标签...</h2>');
            },
            onRequest:   $empty,
            onFailure:   $empty,
            onException: $empty,
            onCancel:    $empty
        });
        req.send();
    }
}

function tag(movieId, anonym){
    if (anonym){
        $('tag').addEvent('click', function(){
            loginDialog(VMS.urlBase + '/movie/' + movieId);
        });
    }else{
        $('tag').addEvent('click', function(){
            tagDialog(movieId, '<h2>标签...</h2>');
        });
    }
}

function tagDialog(movieId, title){
    new Modal({
        'width': 560,
        'title': title
    }).show('<ul class="tag-dialog">'+
            '<li class="tag-form">'+
                '<ul>'+
                '<form name="login" action="' + VMS.urlBase + '/savetag" method="post">'+
                '<input type="hidden" name="movie.id" value="' + movieId + '"/>'+
                '<li><label for="tagNames">标签：</label><input id="tagNames" type="text" name="tagNames" size="40"/></li>'+
                '<li><label for="tagDescr">描述：</label><textarea id="tagDescr" name="tagDescr" cols="35" rows="4"></textarea></li>'+
                '<li><input type="submit" value="submit" class="button"/></li>'+
                '</form>'+
                '</ul>'+
            '</li>'+
            '<li class="tag-list">'+
                '<ul>'+
                '<li id="hot-tags-copy">'+
                '<label>热门标签:</label>'+
                '</li>'+
                '<li id="tags-copy">'+
                '<label>常用标签:</label>'+
                '</li>'+
                '<li id="my-tags-copy">'+
                '<label>我的标签:</label>'+
                '</li>'+
                '</ul>'+
            '</li>'+
            '</ul>'
    );

    var totxt = $('to-tags-can').get('text');
    var tn = $('tagNames');
    tn.setProperty('value', totxt);


    var hotcan = $('hot-tags-can').clone();
    $('hot-tags-copy').adopt(hotcan);

    var mycan = $('my-tags-can').clone();
    $('my-tags-copy').adopt(mycan);

    var can = $('tags-can').clone();
    $('tags-copy').adopt(can);


    $$('.c-tag').each(function(el){
        el.addEvent('click', function(e){
            new Event(e).stop();
            var tg = el.get('text');
            var tv = tn.getProperty('value').trim();
            if (! tv.contains(tg, ' ')){
                tn.setProperty('value', tv + (tv == '' ? tg : ' ' + tg));
            }else{
                tn.setProperty('value', tv.replace(new RegExp('(^|.*?\\s+)' + tg + '(\\s+.*?|$)', 'g'), '$1 $2').clean());
            }
        });
    });
}

function loginDialog(redirect){
    new Modal({
        'title': '<h2>登录</h2>',
        'foot': '<a href="' + VMS.urlBase + '/signup">我还没有注册</a>'
    }).show('<ul class="login">'+
            '<form name="login" action="' + VMS.urlBase + '/login" method="post">'+
            '<input type="hidden" name="redirect" value="' + $pick(redirect, '') + '"/>'+
            '<li><label for="login.idc">邮箱：</label><input id="login.idc" type="text" name="login.idc" size="26"/></li>'+
            '<li><label for="login.passwd">密码：</label><input id="login.passwd" type="password" name="login.passwd" size="26"/></li>'+
            '<li><input type="submit" value="submit" class="button"/></li>'+
            '</form>'+
            '</ul>');
}


///
function vote1(intentId, anonym){
    var v = $('vote1');
    var r = v.getParent();
    var rs = r.get('id').match(/_([\d.]*)_(\d*)_(\d*)_([\d.]*)$/);

    var hot  = rs[1].toFloat();
    var want = rs[2].toInt();
    var seen = rs[3].toInt();
    var rate = rs[4].toFloat();

    var hots = vv(hot);
    if (hots == 10){
        $('vote_u').set('text', 1);
        $('vote_d').set('class', 'u');
        $('vote_d').set('text', 0);
    }else{
        hots = (hots + '').split('.');
        $('vote_u').set('text', hots[0]);
        $('vote_d').set('class', 'd');
        $('vote_d').set('text', '.' + $pick(hots[1], 0));
    }

    var rt = new Rating({
        scale: 10,
        callbacks: {
            onMousemove : function(el){
                var stars = el.getParent();
                $('vote_t').set('text', ((1 + stars.bg.getStyle('left').toFloat()/stars.w/stars.scale) * 10).round(1));
            },
            onMouseleave: function(el){
                $('vote_t').set('text', '');
            }
        }
    });

    rt.inject(v, {
        value: hot,
        mask: true,
        callbacks: {
            onClick: function(el){
                if (anonym){
                    loginDialog();
                }else{
                    var stars = el.getParent();
                    tagDialog1(intentId, (1 + stars.bg.getStyle('left').toFloat()/stars.w/stars.scale).round(2));
                }
            }
        }
    });

    var sm = $('vote_s');
    if (want > 0){
        sm.adopt(new Element('span', {text: want +'人想看'}));
    }
    if (seen > 0){
        sm.adopt(new Element('span', {text: seen + ' 人看过！'}));
    }
    if (rate > 0){
        sm.adopt(new Element('span', {text: '我的评分: ' + vv(rate) + ' ！'}));
    }else{
        sm.adopt(new Element('span', {text: '你还没有评分哦！'}));
    }

}


function tagDialog1(intentId, rating){
    new Modal({
        'width': 560,
        'title': '<h2>收藏...</h2>'
    }).show('<ul class="tag-dialog">'+
            '<form name="login" action="' + VMS.urlBase + '/collect" method="post">'+
            '<input type="hidden" name="intent.id" value="' + intentId + '"/>'+
            '<li id="rating" class="rating"></li>'+
            '<li id="collect" class="collect">'+
            '<label for="intent.collect1">想看：</label><input id="intent.collect1" type="radio" name="intent.collect" value="1" /> '+
            '<label for="intent.collect2">看过：</label><input id="intent.collect2" type="radio" name="intent.collect" value="2" />'+
            '</li>'+
            '<li class="tag-form">'+
                '<ul>'+
                '<input type="hidden" name="intent.rating" id="intent.rating" value="' + rating + '"/>'+
                '<li><label for="tags">标签：</label><input id="tags" type="text" name="tags" size="40"/></li>'+
                '<li><label for="intent.dscr">描述：</label><textarea id="intent.dscr" name="intent.dscr" cols="35" rows="4"></textarea></li>'+
                '<li><input type="submit" value="提 交" class="button"/></li>'+
                '</ul>'+
            '</li>'+
            '<li class="tag-list">'+
                '<ul>'+
                '<li id="hot-tags-copy">'+
                '<label>热门标签:</label>'+
                '</li>'+
                '<li id="tags-copy">'+
                '<label>常用标签:</label>'+
                '</li>'+
                '<li id="my-tags-copy">'+
                '<label>我的标签:</label>'+
                '</li>'+
                '</ul>'+
            '</li>'+
            '</form>'+
            '</ul>'
    );

    var rt = new Rating({
        scale: 10,
        type:  2,
        callbacks: {
            onMousemove : function(el){
                var stars = el.getParent();
                //$('vote_t').set('text', ((1 + stars.bg.getStyle('left').toFloat()/stars.w/stars.scale) * 10).round(1));
            },
            onMouseleave: function(el){
                //$('vote_t').set('text', '');
            }
        }
    });

    rt.inject($('rating'), {
        value: rating,
        callbacks: {
            onClick: function(el){
                var stars = el.getParent();
                $('intent.rating').setProperty('value', (1 + stars.bg.getStyle('left').toFloat()/stars.w/stars.scale).round(2));
            }
        }
    });



    var totxt = $('to-tags-can').get('text');
    var tn = $('tags');
    tn.setProperty('value', totxt);


    var hotcan = $('hot-tags-can').clone();
    $('hot-tags-copy').adopt(hotcan);

    var mycan = $('my-tags-can').clone();
    $('my-tags-copy').adopt(mycan);

    var can = $('tags-can').clone();
    $('tags-copy').adopt(can);


    $$('.c-tag').each(function(el){
        el.addEvent('click', function(e){
            new Event(e).stop();
            var tg = el.get('text');
            var tv = tn.getProperty('value').trim();
            if (! tv.contains(tg, ' ')){
                tn.setProperty('value', tv + (tv == '' ? tg : ' ' + tg));
            }else{
                tn.setProperty('value', tv.replace(new RegExp('(^|.*?\\s+)' + tg + '(\\s+.*?|$)', 'g'), '$1 $2').clean());
            }
        });
    });
}







//\*********************\//

var Player = new Class({
    Implements: [Options],
    options: {
        id: null,
        width: '100%',
        height: '100%',
        container: null,
        properties: {},
        params: {
            quality: 'high',
            allowScriptAccess: 'always',
            wMode: 'transparent',
            swLiveConnect: true,
            allowFullScreen: true,
            atutoplay: true
        },
        callBacks: {},
        vars: {}
    },
    initialize: function(path, options){
        this.setOptions(options);
        this.so      = new Swiff(path, this.options);
        this.co      = $(this.options.container);
        this.postion = this.co.style.position;
        this.top     = this.co.style.top;
        this.left    = this.co.style.left;
        this.width   = this.co.style.width;
        this.height  = this.co.style.height;
        this.isFullscreen = false;
        window.addEvent('resize', function(e){
            if(e) e = new Event(e).stop();
            this.resize();
        }.bind(this));
    },
    fullscreen: function(e){
        if(e) e = new Event(e).stop();
        if (this.isFullscreen){
            try {
                this.isFullscreen = false;
                this.co.style.position = this.position;
                this.co.style.top      = this.top;
                this.co.style.left     = this.left;
                this.co.style.width    = this.width;
                this.co.style.height   = this.height;
                document.location.hash = "";
                document.body.style.overflow = "auto";
            } catch(e){
                alert('problem exit fullscreen: ' + e);
            }
        } else {
            this.isFullscreen = true;
            try {
                this.co.style.position = "absolute";
                this.co.style.top    = 0;
                this.co.style.left   = 0;
                this.co.style.width  = window.getWidth() + "px";
                this.co.style.height = window.getHeight() + "px";
                document.body.style.overflow = 'hidden';
                window.scrollTo(0,0);
            } catch(e){
                alert('problem launch fullscreen: ' + e);
            }
        }
    },
    resize: function(e){
        if(e) e = new Event(e).stop();
        if (this.isFullscreen){
            this.isFullscreen = false;
            this.fullscreen();
        }
    }
});


var Addable = new Class({
    Implements: [Options],
    options: {
        delable: true,
        numable: true
    },
    initialize: function(options){
        this.setOptions(options);
        this.addButton = new Element('span', {});
    },
    make: function(el, options){
        el = $(el);
        options = $merge(this.options, options);
        if (options.delable){
            var delButton = new Element('span', {});
        }

    }
});

var Rating = new Class({
    Implements: [Events, Options],
    options: {
        url:   null,
        async: true,
        scale: 5,
        value: 0,
        type:  1,
        mask:   false,
        locked: false,
        colors: {
            activeColor: '#ff7676',
            votedColor:  '#ffa800',
            fillColor:   '#edeeed'
        },
        stars: {
            1: ['/styles/img/rating1.png', [32, 32]],
            2: ['/styles/img/rating2.png', [23, 22]],
            3: ['/styles/img/rating3.png', [17, 17]]
        },
        callbacks: {
            onMousemove : $empty,
            onMouseenter: $empty,
            onMouseleave: $empty,
            onClick:      $empty
        },
        requests: {
            onComplete:  $empty,
            onSuccess:   $empty,
            onRequest:   $empty,
            onFailure:   $empty,
            onException: $empty,
            onCancel:    $empty
        }
    },
    initialize: function(options){
        this.setOptions(options);

        this.cover = new Element('div', {
            'styles': {
                'position': 'absolute', 'top': 0
            }
        });
        this.star = new Element('div', {
            'styles': {
                'position': 'absolute', 'top': 0
            }
        });
        this.bg = new Element('div', {
            'styles': {
                'position': 'absolute', 'top': 0
            }
        });

        this.stars = new Element('div', {
            'styles': {
                'position': 'relative',
                'float': 'left',
                'overflow': 'hidden'
            }
        });
    },
    inject: function(el, options){
        var co = $(el).empty();
        options = $merge(this.options, options);
        options.value = $pick(options.value, 0);

        var i = options.stars[options.type][0];
        var w = options.stars[options.type][1][0];
        var h = options.stars[options.type][1][1];

        var cover = this.cover.clone();
        cover.set('styles', {
            'width':  w, 'height': h
        });

        var star = this.star.clone();
        star.set('styles', {
            'width':  w, 'height': h
        });
        if (Browser.Engine.trident4){
            star.set('styles', {
                'filter': 'progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src='+ i +')'
            });
        }else{
            star.set('styles', {
                'background': 'transparent url(' + i + ') no-repeat scroll left top'
            });
        }

        var bg = this.bg.clone();
        bg.set('styles', {
            'left': -w * options.scale * (1 - options.value),
            'background': options.colors.votedColor,
            'width':  w * options.scale, 'height': h
        });

        var stars = this.stars.clone();
        stars.set('styles', {
            'background': options.colors.fillColor,
            'width':  w * options.scale, 'height': h
        });
        stars.bg = bg;
        stars.adopt(bg);

        stars.w = w;
        stars.h = h;
        stars.i = i;
        stars.value  = options.value;
        stars.scale  = options.scale;
        stars.mask   = options.mask;
        stars.locked = options.locked;
        stars.colors = options.colors;
        stars.url    = options.url;
        stars.async  = options.async;

        stars.callbacks = options.callbacks;
        stars.requests  = options.requests;

        for (var k=1; k <= options.scale; k++){
            var img = star.clone();
            img.set('styles', {
                'left': (k - 1) * w
            });
            stars.adopt(img);
        }
        co.adopt(stars);

        for (var j=1; j <= options.scale; j++){
            var top = cover.clone();

            top.value = stars.value;
            top.set('styles', {
                'left': (j - 1) * w
            });
            if (! stars.locked){
                top.set({
                    'styles': {
                        'cursor': 'pointer'
                    },
                    'events': {
                        'mouseenter': this.mouseenter.bindWithEvent(this),
                        'mouseleave': this.mouseleave.bindWithEvent(this),
                        'click': this.click.bindWithEvent(this)
                    }
                });
            }
            stars.adopt(top);
        }

        if (stars.locked){
            co.set('title', stars.value);
        }
    },
    mousemove: function(e) {
        if(e) e = new Event(e).stop();
        var stars = e.target.getParent();

        this.changeColor(stars, this.getVote(e), stars.colors.activeColor);
        stars.callbacks.onMousemove(e.target);
    },
    mouseenter: function(e){
        if(e) e = new Event(e).stop();
        var stars = e.target.getParent();

        e.target.addEvent('mousemove', this.mousemove.bindWithEvent(this));
        stars.callbacks.onMouseenter(e.target);
    },
    mouseleave: function(e){
        if(e) e = new Event(e).stop();
        var stars = e.target.getParent();

        e.target.removeEvent(this.mousemove);
        this.changeColor(stars, stars.value, stars.colors.votedColor);
        stars.callbacks.onMouseleave(e.target);
    },
    click: function(e){
        if(e) e = new Event(e).stop();
        var stars = e.target.getParent();
        if (! stars.mask){
            stars.value = this.getVote(e);
            this.changeColor(stars, stars.value, stars.colors.votedColor);
            if (stars.url) {
                var req = new Request({
                    url: stars.url + stars.value,
                    async: stars.async,
                    onComplete: stars.requests.onComplete,
                    onSuccess: stars.requests.onSuccess,
                    onRequest: stars.requests.onRequest,
                    onFailure: stars.requests.onFailure,
                    onException: stars.requests.onException,
                    onCancel: stars.requests.onCancel
                });
                req.send();
            }
        }
        stars.callbacks.onClick(e.target);
    },
    getVote: function(e){
        var stars = e.target.getParent();
        var len = e.client.x - stars.getPosition().x;
        return (len/stars.w/stars.scale).round(2);
    },
    changeColor: function(stars, p, c){
        stars.bg.set('styles', {
            'left': -stars.w * stars.scale * (1 - p), 'background': c
        });
    }
});


var Modal = new Class({
    Implements: [Events, Options],
    options: {
        title: null,
        foot: null,
        width: 400,
        height: 'auto',
        speed: 500,
        maskOpacity: 0.3,
        maskColor: '#000000',
        classPrefix: 'modal',
        onHide: $empty,
        onShow: $empty,
        onStart: $empty
    },
    initialize: function(options){
        this.setOptions(options);
        this.isShowing = false;

        this.mask = new Element('div', {
            'id': 'modal-mask',
            'class': this.options.classPrefix + '-mask',
            'styles': {
                'position': 'absolute',
                'top': 0,
                'left': 0,
                'opacity': 0,
                'z-index': 9999,
                'background-color': this.options.maskColor
            },
            'events': {
                'click': this.hide.bindWithEvent(this)
            }
        });

        this.pop = new Element('div', {
            'id': 'modal-pop',
            'class': this.options.classPrefix + '-pop',
            'styles': {
                'position': 'absolute',
                'visibility': 'hidden',
                'overflow': 'hidden',
//                'width': this.options.width,
                'height': 'auto',
                'left': '50%',
                'z-index': 10000
            }
        });

        this.menu = new Element('div', {
            'class': this.options.classPrefix + '-menu',
            'styles': {
                'float': 'right',
                'height': 8
            }
        }).adopt(new Element('a', {
                'href': '#',
                'text': 'X',
                'events': {
                    'click': this.hide.bindWithEvent(this)
                }
        }));
        this.head = new Element('div', {
            'id': 'modal-head',
            'class': this.options.classPrefix + '-head'
        });
        this.body = new Element('div', {
            'id': 'modal-body',
            'class': this.options.classPrefix + '-body'
        });
        this.foot = new Element('div', {
            'id': 'modal-foot',
            'class': this.options.classPrefix + '-foot'
        });
        this.pop.adopt(this.menu, this.head, this.body, this.foot);

        this.fx = {
            mask: new Fx.Tween(this.mask, {property: 'opacity', duration: this.options.speed}),
            slide: new Fx.Tween(this.pop, {property: 'top', duration:this.options.speed})
        };


        window.addEvents({
            'keyup': this.hide.bindWithEvent(this),
            'resize': this.update.bindWithEvent(this),
            'scroll': this.update.bindWithEvent(this)
        });
        this.fireEvent('onStart');
    },
    show: function(el, options){
        this.head.empty();
        this.body.empty();
        this.foot.empty();

        switch($type(el)) {
            case 'element':
                this.body.adopt(el.clone().cloneEvents(el));
//                this.body.adopt(el);
                break;
            case 'string':
                this.body.set('html', el);
                break;
            default:
                return false;
                break;
        }

        options = $merge(this.options, options);

        this.pop.setStyle('width', options.width);
        this.body.setStyle('height', options.height);
        this.head.set('html', options.title || '');
        this.foot.set('html', options.foot || '');

        if(! this.isShowing){
            $$('object', 'select').setStyle('visibility', 'hidden');
            $$('body').adopt(this.mask, this.pop);

            this.pop.setStyles({
                'top': window.getScroll().y - this.pop.getSize().y,
                'visibility': 'visible',
                'marginLeft': -(this.pop.getSize().x/2)
            });
            this.mask.setStyles({
                'top': -window.getScroll().y,
                'height': window.getScrollSize().y + window.getScroll().y,
                //'height': window.getSize().y,
                'width': window.getSize().x
            });

            this.fx.mask.start(this.options.maskOpacity);
            this.fx.slide.start(window.getScroll().y + (window.getSize().y/2 - this.pop.getSize().y/2));

            this.isShowing = true;

            this.fireEvent('onShow');
        }
    },
    hide: function(e){
        var event = new Event(e).stop();
        if((event.key && event.key != 'esc') || ! this.isShowing) return false;

        $$('object', 'select').setStyle('visibility', 'visible');

        this.fx.slide.cancel();
        this.fx.slide.start(-this.pop.getSize().y).chain(function(){
            this.pop.setStyle('visibility', 'hidden').dispose();

            this.fx.mask.start(0).chain(function(){
                this.mask.dispose();
                this.isShowing = false;
                this.fireEvent('onHide');
            }.bind(this));
        }.bind(this));
    },
    update: function(e){
        if(e) e = new Event(e).stop();
        if(this.isShowing){
            this.fx.slide.cancel();
            var size = window.getSize();
            var scrollSize = window.getScrollSize();
            this.mask.setStyles({
//                'height': (size.y > scrollSize.y) ? size.y : scrollSize.y,
//                'width': size.x
                'height': window.getScrollSize().y + window.getScroll().y,
                'top': -window.getScroll().y
            });
            this.fx.slide.start(window.getScroll().y + (window.getSize().y/2 - this.pop.getSize().y/2))
        }
    }
});

/**
 * Swiff.Uploader - Flash FileReference Control
 *
 * @version		1.1.1
 *
 * @license		MIT License
 *
 * @author		Harald Kirschner <mail [at] digitarald [dot] de>
 * @copyright	Authors
 */

Swiff.Uploader = new Class({

	Extends: Swiff,

	Implements: Events,

	options: {
		path: 'Swiff.Uploader.swf',
		multiple: true,
		queued: true,
		typeFilter: null,
		url: null,
		method: 'post',
		data: null,
		fieldName: 'Filedata',
		callBacks: null
	},

	initialize: function(options){
		if (Browser.Plugins.Flash.version < 9) return false;
		this.setOptions(options);

		var callBacks = this.options.callBacks || this;
		if (callBacks.onLoad) this.addEvent('onLoad', callBacks.onLoad);

		var prepare = {}, self = this;
		['onSelect', 'onAllSelect', 'onCancel', 'onBeforeOpen', 'onOpen', 'onProgress', 'onComplete', 'onError', 'onAllComplete'].each(function(index) {
			var fn = (callBacks[index]) ? callBacks[index] : $empty;
			prepare[index] = function() {
				self.fireEvent(index, arguments, 10);
				return fn.apply(self, arguments);
			};
		});

		prepare.onLoad = this.load.create({delay: 10, bind: this});
		this.options.callBacks = prepare;

		var path = this.options.path;
		if (!path.contains('?')) path += '?noCache=' + $time(); // quick fix

		delete this.options.params.wMode;
		this.parent(path);

		if (!this.options.container) document.body.appendChild(this.object);
		return this;
	},

	load: function(){
		this.remote('register', this.instance, this.options.multiple, this.options.queued);
		this.fireEvent('onLoad');
	},

	/*
	Method: browse
		Open the file browser.
	*/

	browse: function(typeFilter){
		return this.remote('browse', $pick(typeFilter, this.options.typeFilter));
	},

	/*
	Method: upload
		Starts the upload of all selected files.
	*/

	upload: function(options){
//        alert('1 '+ options.url);
        var current = this.options;
		options = $extend({data: current.data, url: current.url, method: current.method, fieldName: current.fieldName}, options);
		if ($type(options.data) == 'element') options.data = $(options.data).toQueryString();
        return this.remote('upload', options);
	},

	/*
	Method: removeFile
		For multiple uploads cancels and removes the given file from queue.

	Arguments:
		name - (string) Filename
		name - (string) Filesize in byte
	*/

	removeFile: function(file){
		if (file) file = {name: file.name, size: file.size};
		return this.remote('removeFile', file);
	},

	/*
	Method: getFileList
		Returns one Array with with arrays containing name and size of the file.

	Returns:
		(array) An array with files
	*/

	getFileList: function(){
		return this.remote('getFileList');
	}

});



/**
 * Fx.ProgressBar
 *
 * @version		1.0
 *
 * @license		MIT License
 *
 * @author		Harald Kirschner <mail [at] digitarald [dot] de>
 * @copyright	Authors
 */

Fx.ProgressBar = new Class({

	Extends: Fx,

	options: {
		text: null,
		transition: Fx.Transitions.Circ.easeOut,
		link: 'cancel'
	},

	initialize: function(element, options) {
		this.element = $(element);
		this.parent(options);
		this.text = $(this.options.text);
		this.set(0);
	},

	start: function(to, total) {
		return this.parent(this.now, (arguments.length == 1) ? to.limit(0, 100) : to / total * 100);
	},

	set: function(to) {
		this.now = to;
		this.element.setStyle('backgroundPosition', (100 - to) + '% 0px');
		if (this.text) this.text.set('text', Math.round(to) + '%');
		return this;
	}

});



/**
 * FancyUpload - Flash meets Ajax for simply working uploads
 *
 * @version		2.0 beta 4
 *
 * @license		MIT License
 *
 * @author		Harald Kirschner <mail [at] digitarald [dot] de>
 * @copyright	Authors
 */

var Fancy = new Class({

	Extends: Swiff.Uploader,

	options: {
        path: '/js/Swiff.Uploader.swf',
		limitSize: false,
		limitFiles: 5,
		instantStart: false,
		allowDuplicates: false,
		validateFile: $lambda(true), // provide a function that returns true for valid and false for invalid files.

		fileInvalid: null, // called for invalid files with error stack as 2nd argument
		fileCreate: null, // creates file element after select
		fileUpload: null, // called when file is opened for upload, allows to modify the upload options (2nd argument) for every upload
		fileComplete: null, // updates the file element to completed state and gets the response (2nd argument)
		fileRemove: null // removes the element
		/**
		 * Events:
		 * onSelect, onAllSelect, onCancel, onBeforeOpen, onOpen, onProgress, onComplete, onError, onAllComplete
		 */
	},

	initialize: function(status, list, options) {
		this.status = $(status);
		this.list = $(list);

		this.files = [];

		if (options.callBacks) {
			this.addEvents(options.callBacks);
			options.callBacks = null;
		}
		this.parent(options);
		this.render();
	},

	render: function() {
		this.overallTitle = this.status.getElement('.overall-title');
		this.currentTitle = this.status.getElement('.current-title');
		this.currentText = this.status.getElement('.current-text');

		var progress = this.status.getElement('.overall-progress');
		this.overallProgress = new Fx.ProgressBar(progress, {
			text: new Element('span', {'class': 'progress-text'}).inject(progress, 'after')
		});
		progress = this.status.getElement('.current-progress')
		this.currentProgress = new Fx.ProgressBar(progress, {
			text: new Element('span', {'class': 'progress-text'}).inject(progress, 'after')
		});
	},

	onLoad: function() {
		this.log('Uploader ready!');
	},

	onBeforeOpen: function(file, options) {
		this.log('Initialize upload for "{name}".', file);
		var fn = this.options.fileUpload;
		return (fn) ? fn.call(this, this.getFile(file), options) : null;
	},

	onOpen: function(file, overall) {
		this.log('Starting upload "{name}".', file);
		file = this.getFile(file);
		file.element.addClass('file-uploading');
		this.currentProgress.cancel().set(0);
		this.currentTitle.set('html', 'File Progress "{name}"'.substitute(file) );
	},

	onProgress: function(file, current, overall) {
		this.overallProgress.start(overall.bytesLoaded, overall.bytesTotal);
		this.currentText.set('html', 'Upload with {rate}/s. Time left: ~{timeLeft}'.substitute({
			rate: (current.rate) ? this.sizeToKB(current.rate) : '- B',
			timeLeft: Date.fancyDuration(current.timeLeft || 0)
		}));
		this.currentProgress.start(current.bytesLoaded, current.bytesTotal);
	},

	onSelect: function(file, index, length) {
		var errors = [];
		if (this.options.limitSize && (file.size > this.options.limitSize)) errors.push('size');
		if (this.options.limitFiles && (this.countFiles() >= this.options.limitFiles)) errors.push('length');
		if (!this.options.allowDuplicates && this.getFile(file)) errors.push('duplicate');
		if (!this.options.validateFile.call(this, file, errors)) errors.push('custom');
		if (errors.length) {
			var fn = this.options.fileInvalid;
			if (fn) fn.call(this, file, errors);
			return false;
		}
		(this.options.fileCreate || this.fileCreate).call(this, file);
		this.files.push(file);
		return true;
	},

	onAllSelect: function(files, current, overall) {
		this.log('Added ' + files.length + ' files, now we have (' + current.bytesTotal + ' bytes).', arguments);
		this.updateOverall(current.bytesTotal);
		this.status.removeClass('status-browsing');
		if (this.files.length && this.options.instantStart) this.upload.delay(10, this);
	},

	onComplete: function(file, response) {
		this.log('Completed upload "' + file.name + '".', arguments);
		this.currentText.set('html', 'Upload complete!');
		this.currentProgress.start(100);
		(this.options.fileComplete || this.fileComplete).call(this, this.finishFile(file), response);
	},

	onError: function(file, error, info) {
		this.log('Upload "' + file.name + '" failed. "{1}": "{2}".', arguments);
		(this.options.fileError || this.fileError).call(this, this.finishFile(file), error, info);
	},

	onCancel: function() {
		this.log('Filebrowser cancelled.', arguments);
		this.status.removeClass('file-browsing');
	},

	onAllComplete: function(current) {
		this.log('Completed all files, ' + current.bytesTotal + ' bytes.', arguments);
		this.updateOverall(current.bytesTotal);
		this.overallProgress.start(100);
		this.status.removeClass('file-uploading');
	},

	browse: function(fileList) {
		var ret = this.parent(fileList);
		if (ret !== true){
			this.log('Browse in progress.');
			if (ret) alert(ret);
		} else {
			this.log('Browse started.');
			this.status.addClass('file-browsing');
		}
	},

	upload: function(options) {
        var ret = this.parent(options);
        if (ret !== true) {
			this.log('Upload in progress or nothing to upload.');
			if (ret) alert(ret);
		} else {
			this.log('Upload started.');
			this.status.addClass('file-uploading');
			this.overallProgress.set(0);
		}
	},

	removeFile: function(file) {
		var remove = this.options.fileRemove || this.fileRemove;
		if (!file) {
			this.files.each(remove, this);
			this.files.empty();
			this.updateOverall(0);
		} else {
			if (!file.element) file = this.getFile(file);
			this.files.erase(file);
			remove.call(this, file);
			this.updateOverall(this.bytesTotal - file.size);
		}
		this.parent(file);
	},

	getFile: function(file) {
		var ret = null;
		this.files.some(function(value) {
			if ((value.name != file.name) || (value.size != file.size)) return false;
			ret = value;
			return true;
		});
		return ret;
	},

	countFiles: function() {
		var ret = 0;
		for (var i = 0, j = this.files.length; i < j; i++) {
			if (!this.files[i].finished) ret++;
		}
		return ret;
	},

	updateOverall: function(bytesTotal) {
		this.bytesTotal = bytesTotal;
		this.overallTitle.set('html', 'Overall Progress (' + this.sizeToKB(bytesTotal) + ')');
	},

	finishFile: function(file) {
		file = this.getFile(file);
		file.element.removeClass('file-uploading');
		file.finished = true;
		return file;
	},

	fileCreate: function(file) {
		file.info = new Element('span', {'class': 'file-info'});
		file.element = new Element('li', {'class': 'file'}).adopt(
			new Element('span', {'class': 'file-size', 'html': this.sizeToKB(file.size)}),
			new Element('a', {
				'class': 'file-remove',
				'href': '#',
				'html': 'Remove',
				'events': {
					'click': function() {
						this.removeFile(file);
						return false;
					}.bind(this)
				}
			}),
			new Element('span', {'class': 'file-name', 'html': file.name}),
			file.info
		).inject(this.list);
	},

	fileComplete: function(file, response) {
		this.options.processResponse || this
		var json = $H(JSON.decode(response, true));
        if (json.get('result') == 'success') {
			file.element.addClass('file-success');
			file.info.set('html', json.get('size'));
		} else {
			file.element.addClass('file-failed');
            file.info.set('html', json.get('error') || response);
		}
	},

	fileError: function(file, error, info) {
		file.element.addClass('file-failed');
		file.info.set('html', '<strong>' + error + '</strong><br />' + info);
	},

	fileRemove: function(file) {
		file.element.fade('out').retrieve('tween').chain(Element.destroy.bind(Element, file.element));
	},

	sizeToKB: function(size) {
		var unit = 'B';
		if ((size / 1048576) > 1) {
			unit = 'MB';
			size /= 1048576;
		} else if ((size / 1024) > 1) {
			unit = 'kB';
			size /= 1024;
		}
		return size.round(1) + ' ' + unit;
	},

	log: function(text, args) {
		if (window.console) console.log(text.substitute(args || {}));
	}

});

/**
 * @todo Clean-up, into Date.js
 */
Date.parseDuration = function(sec) {
	var units = {}, conv = Date.durations;
	for (var unit in conv) {
		var value = Math.floor(sec / conv[unit]);
		if (value) {
			units[unit] = value;
			if (!(sec -= value * conv[unit])) break;
		}
	}
	return units;
};

Date.fancyDuration = function(sec) {
	var ret = [], units = Date.parseDuration(sec);
	for (var unit in units) ret.push(units[unit] + Date.durationsAbbr[unit]);
	return ret.join(', ');
};

Date.durations = {years: 31556926, months: 2629743.83, days: 86400, hours: 3600, minutes: 60, seconds: 1, milliseconds: 0.001};
Date.durationsAbbr = {
	years: 'j',
	months: 'm',
	days: 'd',
	hours: 'h',
	minutes: 'min',
	seconds: 'sec',
	milliseconds: 'ms'
};
