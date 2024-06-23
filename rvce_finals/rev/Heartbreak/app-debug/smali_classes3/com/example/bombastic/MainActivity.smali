.class public Lcom/example/bombastic/MainActivity;
.super Landroidx/appcompat/app/AppCompatActivity;
.source "MainActivity.java"


# instance fields
.field private Wnsdjkywuedc:I

.field private binding:Lcom/example/bombastic/databinding/ActivityMainBinding;

.field public encryptedText:Ljava/lang/String;

.field public key:Ljava/lang/String;

.field public pop:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 15
    const-string v0, "bombastic"

    invoke-static {v0}, Ljava/lang/System;->loadLibrary(Ljava/lang/String;)V

    .line 16
    return-void
.end method

.method public constructor <init>()V
    .locals 1

    .line 11
    invoke-direct {p0}, Landroidx/appcompat/app/AppCompatActivity;-><init>()V

    .line 21
    invoke-virtual {p0}, Lcom/example/bombastic/MainActivity;->Encrypt()Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/example/bombastic/MainActivity;->pop:Ljava/lang/String;

    .line 54
    const/4 v0, 0x0

    iput v0, p0, Lcom/example/bombastic/MainActivity;->Wnsdjkywuedc:I

    .line 55
    iget-object v0, p0, Lcom/example/bombastic/MainActivity;->pop:Ljava/lang/String;

    iput-object v0, p0, Lcom/example/bombastic/MainActivity;->encryptedText:Ljava/lang/String;

    .line 58
    const-string v0, "fakekey1234qwert6"

    iput-object v0, p0, Lcom/example/bombastic/MainActivity;->key:Ljava/lang/String;

    return-void
.end method

.method private IreallyLovedHerGuys13(Ljava/lang/String;)Ljava/lang/String;
    .locals 5
    .param p1, "input"    # Ljava/lang/String;

    .line 23
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    .line 24
    .local v0, "result":Ljava/lang/StringBuilder;
    const/4 v1, 0x0

    .local v1, "i":I
    :goto_0
    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result v2

    if-ge v1, v2, :cond_2

    .line 25
    invoke-virtual {p1, v1}, Ljava/lang/String;->charAt(I)C

    move-result v2

    .line 26
    .local v2, "ch":C
    invoke-static {v2}, Ljava/lang/Character;->isLetter(C)Z

    move-result v3

    if-eqz v3, :cond_1

    .line 27
    invoke-static {v2}, Ljava/lang/Character;->isLowerCase(C)Z

    move-result v3

    if-eqz v3, :cond_0

    const/16 v3, 0x61

    goto :goto_1

    :cond_0
    const/16 v3, 0x41

    .line 28
    .local v3, "base":C
    :goto_1
    sub-int v4, v2, v3

    add-int/lit8 v4, v4, 0xd

    rem-int/lit8 v4, v4, 0x1a

    add-int/2addr v4, v3

    int-to-char v2, v4

    .line 30
    .end local v3    # "base":C
    :cond_1
    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 24
    .end local v2    # "ch":C
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 32
    .end local v1    # "i":I
    :cond_2
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    return-object v1
.end method

.method private SheWasMyCrushX(Ljava/lang/String;)Ljava/lang/String;
    .locals 3
    .param p1, "input"    # Ljava/lang/String;

    .line 36
    invoke-virtual {p1}, Ljava/lang/String;->toCharArray()[C

    move-result-object v0

    .line 37
    .local v0, "inputChars":[C
    const/4 v1, 0x0

    .local v1, "i":I
    :goto_0
    array-length v2, v0

    if-ge v1, v2, :cond_0

    .line 38
    aget-char v2, v0, v1

    xor-int/lit8 v2, v2, 0x2d

    int-to-char v2, v2

    aput-char v2, v0, v1

    .line 37
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 40
    .end local v1    # "i":I
    :cond_0
    new-instance v1, Ljava/lang/String;

    invoke-direct {v1, v0}, Ljava/lang/String;-><init>([C)V

    return-object v1
.end method

.method static synthetic access$000(Lcom/example/bombastic/MainActivity;)I
    .locals 1
    .param p0, "x0"    # Lcom/example/bombastic/MainActivity;

    .line 11
    iget v0, p0, Lcom/example/bombastic/MainActivity;->Wnsdjkywuedc:I

    return v0
.end method

.method static synthetic access$002(Lcom/example/bombastic/MainActivity;I)I
    .locals 0
    .param p0, "x0"    # Lcom/example/bombastic/MainActivity;
    .param p1, "x1"    # I

    .line 11
    iput p1, p0, Lcom/example/bombastic/MainActivity;->Wnsdjkywuedc:I

    return p1
.end method

.method static synthetic access$008(Lcom/example/bombastic/MainActivity;)I
    .locals 2
    .param p0, "x0"    # Lcom/example/bombastic/MainActivity;

    .line 11
    iget v0, p0, Lcom/example/bombastic/MainActivity;->Wnsdjkywuedc:I

    add-int/lit8 v1, v0, 0x1

    iput v1, p0, Lcom/example/bombastic/MainActivity;->Wnsdjkywuedc:I

    return v0
.end method

.method static synthetic access$100(Lcom/example/bombastic/MainActivity;Ljava/lang/String;)Ljava/lang/String;
    .locals 1
    .param p0, "x0"    # Lcom/example/bombastic/MainActivity;
    .param p1, "x1"    # Ljava/lang/String;

    .line 11
    invoke-direct {p0, p1}, Lcom/example/bombastic/MainActivity;->IreallyLovedHerGuys13(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method static synthetic access$200(Lcom/example/bombastic/MainActivity;Ljava/lang/String;)Ljava/lang/String;
    .locals 1
    .param p0, "x0"    # Lcom/example/bombastic/MainActivity;
    .param p1, "x1"    # Ljava/lang/String;

    .line 11
    invoke-direct {p0, p1}, Lcom/example/bombastic/MainActivity;->SheWasMyCrushX(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method


# virtual methods
.method public BorkenHeart(I)Ljava/lang/String;
    .locals 6
    .param p1, "number"    # I

    .line 44
    const/16 v0, 0x10

    new-array v0, v0, [I

    fill-array-data v0, :array_0

    .line 45
    .local v0, "numbers":[I
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    .line 46
    .local v1, "sb":Ljava/lang/StringBuilder;
    array-length v2, v0

    const/4 v3, 0x0

    :goto_0
    if-ge v3, v2, :cond_0

    aget v4, v0, v3

    .line 47
    .local v4, "num":I
    int-to-char v5, v4

    .line 48
    .local v5, "letter":C
    invoke-virtual {v1, v5}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 46
    .end local v4    # "num":I
    .end local v5    # "letter":C
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    .line 50
    :cond_0
    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-direct {p0, v2}, Lcom/example/bombastic/MainActivity;->IreallyLovedHerGuys13(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    return-object v2

    :array_0
    .array-data 4
        0x31
        0x34
        0x6d
        0x37
        0x68
        0x33
        0x6b
        0x33
        0x79
        0x62
        0x72
        0x30
        0x37
        0x68
        0x33
        0x72
    .end array-data
.end method

.method public native Encrypt()Ljava/lang/String;
.end method

.method public native hello()Ljava/lang/String;
.end method

.method protected onCreate(Landroid/os/Bundle;)V
    .locals 2
    .param p1, "savedInstanceState"    # Landroid/os/Bundle;

    .line 62
    invoke-super {p0, p1}, Landroidx/appcompat/app/AppCompatActivity;->onCreate(Landroid/os/Bundle;)V

    .line 63
    invoke-virtual {p0}, Lcom/example/bombastic/MainActivity;->getLayoutInflater()Landroid/view/LayoutInflater;

    move-result-object v0

    invoke-static {v0}, Lcom/example/bombastic/databinding/ActivityMainBinding;->inflate(Landroid/view/LayoutInflater;)Lcom/example/bombastic/databinding/ActivityMainBinding;

    move-result-object v0

    iput-object v0, p0, Lcom/example/bombastic/MainActivity;->binding:Lcom/example/bombastic/databinding/ActivityMainBinding;

    .line 64
    iget-object v0, p0, Lcom/example/bombastic/MainActivity;->binding:Lcom/example/bombastic/databinding/ActivityMainBinding;

    invoke-virtual {v0}, Lcom/example/bombastic/databinding/ActivityMainBinding;->getRoot()Landroidx/constraintlayout/widget/ConstraintLayout;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/example/bombastic/MainActivity;->setContentView(Landroid/view/View;)V

    .line 65
    iget-object v0, p0, Lcom/example/bombastic/MainActivity;->binding:Lcom/example/bombastic/databinding/ActivityMainBinding;

    iget-object v0, v0, Lcom/example/bombastic/databinding/ActivityMainBinding;->textView:Landroid/widget/TextView;

    new-instance v1, Lcom/example/bombastic/MainActivity$1;

    invoke-direct {v1, p0}, Lcom/example/bombastic/MainActivity$1;-><init>(Lcom/example/bombastic/MainActivity;)V

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 87
    return-void
.end method

.method public native stringFromJNI()Ljava/lang/String;
.end method
