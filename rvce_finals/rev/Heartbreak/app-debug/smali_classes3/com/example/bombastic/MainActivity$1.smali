.class Lcom/example/bombastic/MainActivity$1;
.super Ljava/lang/Object;
.source "MainActivity.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/example/bombastic/MainActivity;->onCreate(Landroid/os/Bundle;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/example/bombastic/MainActivity;


# direct methods
.method constructor <init>(Lcom/example/bombastic/MainActivity;)V
    .locals 0
    .param p1, "this$0"    # Lcom/example/bombastic/MainActivity;

    .line 65
    iput-object p1, p0, Lcom/example/bombastic/MainActivity$1;->this$0:Lcom/example/bombastic/MainActivity;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 5
    .param p1, "v"    # Landroid/view/View;

    .line 68
    iget-object v0, p0, Lcom/example/bombastic/MainActivity$1;->this$0:Lcom/example/bombastic/MainActivity;

    invoke-static {v0}, Lcom/example/bombastic/MainActivity;->access$008(Lcom/example/bombastic/MainActivity;)I

    .line 69
    iget-object v0, p0, Lcom/example/bombastic/MainActivity$1;->this$0:Lcom/example/bombastic/MainActivity;

    invoke-static {v0}, Lcom/example/bombastic/MainActivity;->access$000(Lcom/example/bombastic/MainActivity;)I

    move-result v0

    const/16 v1, 0x28

    if-ne v0, v1, :cond_0

    .line 71
    const/4 v0, 0x0

    :try_start_0
    iget-object v1, p0, Lcom/example/bombastic/MainActivity$1;->this$0:Lcom/example/bombastic/MainActivity;

    iget-object v2, p0, Lcom/example/bombastic/MainActivity$1;->this$0:Lcom/example/bombastic/MainActivity;

    iget-object v2, v2, Lcom/example/bombastic/MainActivity;->encryptedText:Ljava/lang/String;

    iget-object v3, p0, Lcom/example/bombastic/MainActivity$1;->this$0:Lcom/example/bombastic/MainActivity;

    iget-object v3, v3, Lcom/example/bombastic/MainActivity;->key:Ljava/lang/String;

    invoke-static {v2, v3}, Lcom/example/bombastic/AESUtils;->decrypt(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Lcom/example/bombastic/MainActivity;->access$100(Lcom/example/bombastic/MainActivity;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 72
    .local v1, "AndSheLeftMe":Ljava/lang/String;
    iget-object v2, p0, Lcom/example/bombastic/MainActivity$1;->this$0:Lcom/example/bombastic/MainActivity;

    invoke-static {v2, v1}, Lcom/example/bombastic/MainActivity;->access$200(Lcom/example/bombastic/MainActivity;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    .line 74
    .local v2, "AndIWasLeftAlone":Ljava/lang/String;
    iget-object v3, p0, Lcom/example/bombastic/MainActivity$1;->this$0:Lcom/example/bombastic/MainActivity;

    const-string v4, "Decryption Sucess"

    invoke-static {v3, v4, v0}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v3

    invoke-virtual {v3}, Landroid/widget/Toast;->show()V

    .line 75
    const-string v3, "This was My Lovely Story"

    invoke-static {v3, v2}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 80
    nop

    .end local v1    # "AndSheLeftMe":Ljava/lang/String;
    .end local v2    # "AndIWasLeftAlone":Ljava/lang/String;
    goto :goto_0

    .line 76
    :catch_0
    move-exception v1

    .line 77
    .local v1, "e":Ljava/lang/Exception;
    invoke-virtual {v1}, Ljava/lang/Exception;->printStackTrace()V

    .line 78
    iget-object v2, p0, Lcom/example/bombastic/MainActivity$1;->this$0:Lcom/example/bombastic/MainActivity;

    const-string v3, "Decryption failed Try Harder..! "

    invoke-static {v2, v3, v0}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v2

    invoke-virtual {v2}, Landroid/widget/Toast;->show()V

    .line 82
    .end local v1    # "e":Ljava/lang/Exception;
    :goto_0
    iget-object v1, p0, Lcom/example/bombastic/MainActivity$1;->this$0:Lcom/example/bombastic/MainActivity;

    invoke-static {v1, v0}, Lcom/example/bombastic/MainActivity;->access$002(Lcom/example/bombastic/MainActivity;I)I

    .line 85
    :cond_0
    return-void
.end method
