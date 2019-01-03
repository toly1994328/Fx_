# Fx
函数绘制测试
#### 零、前言
>这篇是为了下一篇做点铺垫，也是来复习一些数学基础  
本篇属于休闲娱乐，不要太较真，小科普一下，不喜勿喷   
本文知识点前4点你可以随便看看，但`第5点非常重要`,本文源码见`捷文规范`  

>本文知识点：  
1)数学函数的概念     
2)直角坐标系的下函数图形  
3)极坐标下的函数图象    
4)参数方程下的函数图形  
5)正弦函数的详细分析(为下一篇文章做铺垫)


---

#### 一、数学函数的概念： 
##### 1.高中数学必修1：

```
设A,B为非空的数集，如果按照某种确定的对应关系f,  
使对于集合A中的任意的任意一个数x,在集合B中都有唯一确定的数f(x)和它对应，
那么就称"f:A→B"为从集合A到集合B的一个函数，记作：
y=f(x),x∈A

其中，x叫做自变量，x的取值范围叫做函数的[定义域]
与x的值对应的y值叫做函数值，函数值的集合{f(x)|x∈A}叫做函数的[值域]
```

---

##### 2.大学高等数学

```
设数集D⊂ R,则称映射f:D→R为定义在D上的函数，通常简记为
y=f(x),x∈ D

其中x称自变量，y称因变量，D称定义域，记作Df,即Df=D.
值域：Rf=f(D)={y|y=f(x),x∈ D}
```

---

##### 3.映射：

```
设X,Y是两个非集合，如果存在一个法则f，使的对X中的每个元素x,
按法则f,在Y中有唯一确定的元素y与之对应，则称f为X到Y的映射，记作
f:X→Y

其中y称为元素x(在映射f下)的像，并记作f(x),即y=f(x)
而元素x称为元素y(在映射f下)的原像
```

---

#### 二、直角坐标系的下函数图形
>这里只是模拟函数，然后绘制出可视的图象  
数学中的实数是连续的，这里在屏幕中将像素作为基本的单元  
绘图核心：点集成线，单点半径1px  
自变量：x  
定义域：Df用集合Set表示  
函数关系：函数f(x)   
点集用Map表示，x→y  
 
---

##### 0.网格与坐标系的绘制
>网格和坐标系我已经封装，初始View如下：

```
public class MathView extends View {
    private Point mCoo = new Point(500, 700);//坐标系
    private Picture mCooPicture;//坐标系canvas元件
    private Picture mGridPicture;//网格canvas元件
    private Paint mHelpPint;//辅助画笔
    private Paint mPaint;//主画笔
    private Path mPath;//主路径
    public MathView(Context context) {
        this(context, null);
    }

    public MathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();//初始化
    }

    private void init() {
        //初始化主画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        //初始化主路径
        mPath = new Path();

        //初始化辅助
        mHelpPint = HelpDraw.getHelpPint(Color.RED);
        mCooPicture = HelpDraw.getCoo(getContext(), mCoo);
        mGridPicture = HelpDraw.getGrid(getContext());
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        HelpDraw.draw(canvas, mGridPicture, mCooPicture);
        canvas.save();
        canvas.translate(mCoo.x, mCoo.y);
        canvas.scale(1, -1);//y轴向上
        canvas.restore();
    }

```

![网格和坐标系准备.png](https://upload-images.jianshu.io/upload_images/9414344-dd144dd8200b2474.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


>具体细节这里不说了，详见：[Android关于Canvas你所知道的和不知道的一切](https://juejin.im/post/5be29aa2e51d45228170ff33),或源码

---

##### 1.一次函数：y=x,定义域[-200,300]

![y=x.png](https://upload-images.jianshu.io/upload_images/9414344-5a8f852b6764b4b0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

---

###### 1.1：几个成员变量

```
private TreeSet<Float> Df = new TreeSet<>();//定义域
private Map<Float, Float> funMap = new HashMap<>();//映射表
private Paint mTextPaint;//文字画笔
```


---

###### 1.2:初始化定义域

```
/**
 * 初始化定义域
 */
private void initDf() {
    for (float i = -200; i <= 300; i++) {
        Df.add(i);//初始化定义域
    }
}
```


---

###### 1.3：对应法则fx

```
/**
 * 对应法则
 * @param x 原像(自变量)
 * @return 像(因变量)
 */
private float f(Float x) {
    float y = x;
    return y;
}
```

---

###### 1.4:遍历定义域,将原像x和像f(x)加入映射表

```
/**
 * 遍历定义域,将原像x和像f(x)加入映射表
 */
private void map() {
    Df.forEach(x -> {
        funMap.put(x, f(x));
    });
    //添加所有点
}
```


---

###### 1.5:绘制映射表

```
/**
 * 绘制映射表
 * @param canvas 画笔
 * @param map 点集映射表
 */
private void drawMap(Canvas canvas, Map<Float, Float> map) {
    map.forEach((k, v) -> {
        canvas.drawPoint(k, v, mPaint);
    });
}
```

---

##### 2.绝对值函数：y=|x|,定义域[-200,300]
>只需改一点

![y=abs(x).png](https://upload-images.jianshu.io/upload_images/9414344-598910eca838e853.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

```
/**
 * 对应法则
 * @param x 原像(自变量)
 * @return 像(因变量)
 */
private float f(Float x) {
    float y=Math.abs(x);
    return y;
}
```

---

##### 3.二次函数,定义域[-200,300]

![二次函数.png](https://upload-images.jianshu.io/upload_images/9414344-f751447eb308a756.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


```
/**
 * 对应法则
 * @param x 原像(自变量)
 * @return 像(因变量)
 */
private float f(Float x) {
    float y=(x - 100) * (x - 100) / 200 + 100;
    return y;
}
```

---

##### 4.对数函数：log10(x)为例,定义域[1,1000]
![log10.png](https://upload-images.jianshu.io/upload_images/9414344-a8ae427dd0a9ef21.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

```
/**
 * 初始化定义域
 */
private void initDf() {
    for (float i = 1; i <= 1000; i++) {
        Df.add(i);//初始化定义域
    }
}

/**
 * 对应法则
 *
 * @param x 原像(自变量)
 * @return 像(因变量)
 */
private float f(Float x) {
    float y = (float) (100.f * Math.log10(x));
    return y;
}
```

---

##### 5.指数函数：定义域[-400,500]
![指数函数.png](https://upload-images.jianshu.io/upload_images/9414344-af0c64d9908255a4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



```
/**
 * 初始化定义域
 */
private void initDf() {
    for (float i = -400; i <= 500; i++) {
        Df.add(i);//初始化定义域
    }
}

/**
 * 对应法则
 *
 * @param x 原像(自变量)
 * @return 像(因变量)
 */
private float f(Float x) {
    float y= 100*(float) Math.pow(Math.E,x/300f);
    return y;
}
```

---

##### 6.正弦函数：定义域[-360°,450°]
![正弦函数.png](https://upload-images.jianshu.io/upload_images/9414344-e37c27a334b391b2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

```
/**
 * 初始化定义域
 */
private void initDf() {
    for (float i =-360; i <= 450; i++) {
        Df.add(i);//初始化定义域
    }
}

/**
 * 对应法则
 *
 * @param x 原像(自变量)
 * @return 像(因变量)
 */
private float f(Float x) {
    float y= (float) (100*Math.sin(Math.PI/180*x));
    return y;
}
```

>经历过上面几个函数的绘制，不难发现，只有更改对应法则，即函数关系式就可以了  

---

#### 三、极坐标下的函数图象
>1).寻找角度thta和长度p的函数关系  
2).使用极坐标与直角坐标系的转换关系来绘制点集  

---

##### 1.笛卡尔心型线：`ρ= 100*(1-cosθ)`

![极坐标方程--笛卡尔心型线.png](https://upload-images.jianshu.io/upload_images/9414344-145636f4dae6fe3f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


```
/**
 * 初始化定义域
 */
private void initDf() {
    for (float i = 1; i <= 360; i++) {
        Df.add(i);//初始化定义域
    }
}

/**
 * 绘制映射表
 *
 * @param canvas 画笔
 * @param map    点集映射表
 */
private void drawMap(Canvas canvas, Map<Float, Float> map) {
    map.forEach((thta, p) -> {
        Log.e(TAG, "drawMap: "+p+thta);
        canvas.drawPoint((float) (p * Math.cos(thta)), (float) (p * Math.sin(thta)), mPaint);
    });
}

/**
 * 对应法则
 *
 * @param thta 原像(自变量)
 * @return 像(因变量)
 */
private float f(Float thta) {
    float p = (float) (100 * (1 - Math.cos(thta)));
    return p;
}

/**
 * 遍历定义域,将原像x和像f(x)加入映射表
 */
private void map() {
    Df.forEach(x -> {
        float thta = (float) (Math.PI / 180 * x);
        funMap.put(thta, f(thta));
    });
    //添加所有点
}
```

---
##### 2.四叶草：`ρ= 100*(1-4*sinθ)`
![极坐标方程--四叶草.png](https://upload-images.jianshu.io/upload_images/9414344-c4bd06649ac56597.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

```
/**
 * 对应法则
 *
 * @param thta 原像(自变量)
 * @return 像(因变量)
 */
private float f(Float thta) {
    float p = (float) (100 * (1 - Math.sin(4 * thta)));
    return p;
}
```

---
##### 3.画着玩：`ρ=(e^(cosθ)- 2cos(4θ) + [sin(θ/12)]^5)*100`
![极坐标方程--画着玩.png](https://upload-images.jianshu.io/upload_images/9414344-02903f51ce7b7f69.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

```
/**
 * 对应法则
 *
 * @param thta 原像(自变量)
 * @return 像(因变量)
 */
private float f(Float thta) {
    float p = (float) (100f*(Math.pow(Math.E,Math.cos(thta)) - 2 * Math.cos(4 * thta) + Math.pow(Math.sin(thta / 12), 5)));;
    return p;
}
```

---
##### 4.涡旋线：`ρ= a*θ`
![涡旋线.png](https://upload-images.jianshu.io/upload_images/9414344-722c9c14eff7da87.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

```
/**
 * 对应法则
 *
 * @param thta 原像(自变量)
 * @return 像(因变量)
 */
private float f(Float thta) {
    float p = 30*thta;
    return p;
}
```

---

##### 5.极坐标下的圆
![极坐标下的圆.png](https://upload-images.jianshu.io/upload_images/9414344-7dc5c402c78a2dd3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

```
/**
 * 对应法则
 *
 * @param thta 原像(自变量)
 * @return 像(因变量)
 */
private float f(Float thta) {
    float p = 200;
    return p;
}

```


---

#### 四、参数方程下的函数图象
##### 1.双曲线：`x=a/cosα`,`y=btanα`
![参数方程模拟双曲线.png](https://upload-images.jianshu.io/upload_images/9414344-78ebcb6a91516378.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

```
    /**
     * 初始化定义域
     */
    private void initDf() {
        for (float i = 0; i <= 360 ; i++) {
            Df.add(i);//初始化定义域
        }
    }

    /**
     * 绘制映射表
     *
     * @param canvas 画笔
     * @param map    点集映射表
     */
    private void drawMap(Canvas canvas, Map<Float, Float> map) {
        map.forEach((k, v) -> {
            canvas.drawPoint(k, v, mPaint);
        });
    }

    /**
     * 对应法则
     *
     * @param thta 原像(自变量)
     * @return y像(因变量)
     */
    private float y(Float thta) {
        float y = (float) (100 * Math.tan(thta));
        return y;
    }

    /**
     * 对应法则
     *
     * @param thta 原像(自变量)
     * @return x像(因变量)
     */
    private float x(Float thta) {
        float x = (float) (200 / Math.cos(thta));
        return x;
    }

    /**
     * 遍历定义域,将原像x和像f(x)加入映射表
     */
    private void map() {
        Df.forEach(x -> {
            float thta = (float) (Math.PI / 180 * x);
            funMap.put(x(thta), y(thta));
        });
        //添加所有点
    }
```

---

##### 2.椭圆：`x=a*cosα`,`y=bsinα`
![椭圆.png](https://upload-images.jianshu.io/upload_images/9414344-5cc7d973e20c95b6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

```
/**
 * 对应法则
 *
 * @param thta 原像(自变量)
 * @return y像(因变量)
 */
private float y(Float thta) {
    float y = (float) (300 * Math.sin(thta));
    return y;
}
/**
 * 对应法则
 *
 * @param thta 原像(自变量)
 * @return x像(因变量)
 */
private float x(Float thta) {
    float x = (float) (400 * Math.cos(thta));
    return x;
}
```

---

##### 3.参数方程:双钮线`x=a√(cos2θ )cosθ `,`y=a√(cos2θ)sinθ `
![双钮线.png](https://upload-images.jianshu.io/upload_images/9414344-5a259d5a339c0ce8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

```
/**
 * 对应法则:y=a√(cos2θ)sinθ
 *
 * @param thta 原像(自变量)
 * @return y像(因变量)
 */
private float y(Float thta) {
    float y = (float) (200 * Math.sqrt(Math.cos(2*thta))*Math.sin(thta));
    return y;
}

/**
 * 对应法则:x=a√(cos2θ )cosθ
 *
 * @param thta 原像(自变量)
 * @return x像(因变量)
 */
private float x(Float thta) {
    float x = (float) (200 * Math.sqrt(Math.cos(2*thta))*Math.cos(thta));
    return x;
}
```

---


#### 五、分析与优化
##### 1.分析
>你可能已经吐槽了:什么鬼，怎么后面都是断断续续的点拼成的  
等等...先别急，我们来看看这幅图能说明什么?  
先看一下定义域: [-360,450]，共810个点，每个点半径1px，每个点横向距离1px  
点密集则说明相邻两点间的dy很小，相反，稀疏则说明相邻两点间的dy很大  
也就是密集说明函数变化的幅度小，稀疏说明函数变化的幅度大  
当相邻两点距离大于圆的直径（2px）时，视觉上会看出两个点，即不连续。

![断续的点问题.png](https://upload-images.jianshu.io/upload_images/9414344-fde5b7cbaffcbc06.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

---

##### 2.分析总结
>为了方便描述，这里定义了几个概念

```
如果把一条完美的函数曲线看作P，
那所有现实中(纸、屏幕)的函数图象P'都是对P的取点模拟，
从P上取点的行为称为[取样]，
采样的个数称为[取样总数]，
取样的相邻两点xn,xn+1间的距离称为[取样距离dxn]
当每个dxn值都相等的时，称为[等距采样]
两个样本点pn,pn+1之间的距离称为[样本距离dpn]
```

---

##### 3.看一下连续的点有哪些
>在加入点集时过滤掉相邻两点间距离大于直径的点

```
/**
 * 两点间的距离
 * @return
 */
private float dis(float x0, float y0, float x1, float y1) {
    return (float) Math.sqrt((x0 - x1) * (x0 - x1) + (y0 - y1) * (y0 - y1));
}
```

```
/**
 * 遍历定义域,将原像x和像f(x)加入映射表
 */
private void map() {
    Df.forEach(x -> {
        float dis = dis(x, f(x), x + 1, f(x + 1));//每相邻两点间距离
        if (dis < mLineWidth && dis > mLineWidth / 2) {
            funMap.put(x, f(x));
        }
    });
```

![连续点.png](https://upload-images.jianshu.io/upload_images/9414344-3b76a58f617c9d0a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

---

##### 4.不行连续的点处理思路：
>思路也就是在间距处再取样

![处理](https://upload-images.jianshu.io/upload_images/9414344-a36655386b493c53.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

```
/**
 * 遍历定义域,将原像x和像f(x)加入映射表
 */
private void map() {
    Df.forEach(x -> {
        float dis = dis(x, f(x), x + 1, f(x + 1));//每相邻两点间距离
        if (dis < mLineWidth && dis > mLineWidth / 2) {
            funMap.put(x, f(x));
        } else if (dis > mLineWidth) {
            float num = dis / mLineWidth;//在切割数
            for (float di = 0; di <= num; di += (1.f / num)) {
                x += di;
                funMap.put(x, f(x));
            }
        }
    });
    //添加所有点
}
```

![优化后.png](https://upload-images.jianshu.io/upload_images/9414344-c7d454ac7a3125c6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

---

#### 六、正弦函数的详细分析
##### 1.正弦函数简介

![正弦函数表达式.png](https://upload-images.jianshu.io/upload_images/9414344-aa4f1f61222cc46c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

```
其中A，ω，φ，k是常数，且ω≠0
振幅:A
角频率:ω
周期:T=2π/ω 
频率:f=1/T=ω/2π
相位:ωx+φ
初相:φ
平衡线：y=k
波峰：最大值|A|
波谷：最小值-|A|
```

---
##### 2.振幅A:`离开平衡位置的最大距离`
>下面横轴的每格代表90°,化为弧度制表示即：π/2,每四格是360°,即2π

###### 2.1：`A=300`
![A=300.png](https://upload-images.jianshu.io/upload_images/9414344-ba28fb12bdb5a2be.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



---

###### 2.2：`A=100`
![A=100.png](https://upload-images.jianshu.io/upload_images/9414344-9d6315bf5d5adb7e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

---

###### 2.3:振幅的作用

```
决定正弦曲线的波峰与波谷，形象来说就是"高矮" 
振幅越大，波峰越高，波谷越低，每个周期的图象显得"高"
```

---
##### 3.角频率ω:`单位时间内变化的相角弧度值`
###### 3.1：`ω=2`
![ω=2.png](https://upload-images.jianshu.io/upload_images/9414344-032c9835739698ec.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

---

###### 3.2：`ω=5`
![ω=5.png](https://upload-images.jianshu.io/upload_images/9414344-84570756ad801b4c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

---

###### 3.3：角频率的作用

```
决定正弦曲线的周期，形象来说就是"胖瘦" 
角频率越大，周期越小，每个周期的图象显得"瘦"

ω=2 周期：T = 2π/ω = π 从图中看，每两格一周期,即π 
频率：f = 1/T = 1/π
```

---

##### 4.初相φ：`x=0时的相位`
###### 4.1:`φ=π/6`
![φ=π/6](https://upload-images.jianshu.io/upload_images/9414344-94308021bdbe2b4f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

---
###### 4.2:`φ=π/2`
![φ=π/2](https://upload-images.jianshu.io/upload_images/9414344-8700e1557c55820f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

---

###### 4.3:振幅的作用

```
相位决定了标准正弦函数的左右偏移:正左偏，负右偏，偏移量：φ/ω
```

---

##### 5.平衡值k：`决定平衡线的位置`
###### 5.1:`k=100`
![k=100.png](https://upload-images.jianshu.io/upload_images/9414344-39879621a018d8d3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

---
###### 5.2:`k=200`

![k=200.png](https://upload-images.jianshu.io/upload_images/9414344-63e9d61e9b8b567c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
---



###### 5.3:平衡值的作用

```
平衡值决定标准正弦函数的上下偏移：正上偏，负下偏，偏移量：k
```

>现在对于几个正弦函数的参数值已经有了一点了解,本篇结束  

---

##### 附录：一些常用符号：

← | ↑ |→|↓
---|---|---|---
∪  | ∩|⊂ |⊃
∈  | ∝|⊆ |⊇
∞  | θ|ρ |φ
π|α |β |γ
η|μ |ζ |Ω

---

#### 后记：捷文规范
##### 1.本文成长记录及勘误表
[项目源码](https://github.com/toly1994328/Fx) | 日期|备注
---|---|---
[V0.1-github](https://github.com/toly1994328/Fx)|2018-1-2|[Android绘制函数图象及正弦函数的介绍](https://www.jianshu.com/p/ae4e69437efb)


##### 2.更多关于我

笔名 | QQ|微信|爱好
---|---|---|---|
张风捷特烈 | 1981462002|zdl1994328|语言
 [我的github](https://github.com/toly1994328)|[我的简书](https://www.jianshu.com/u/e4e52c116681)|[我的掘金](https://juejin.im/user/5b42c0656fb9a04fe727eb37)|[个人网站](http://www.toly1994.com)

##### 3.声明
>1----本文由张风捷特烈原创,转载请注明  
2----欢迎广大编程爱好者共同交流  
3----个人能力有限，如有不正之处欢迎大家批评指证，必定虚心改正   
4----看到这里，我在此感谢你的喜欢与支持

---

![icon_wx_200.png](https://upload-images.jianshu.io/upload_images/9414344-8a0c95a090041a0d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
