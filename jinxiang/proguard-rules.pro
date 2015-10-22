# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\ANDROID-SDK\android-sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}


#-keepattributes *Annotation*
#-keep public class com.google.vending.licensing.ILicensingService
#-keep public class com.android.vending.licensing.ILicensingService
#
## For native methods, see http://proguard.sourceforge.net/manual/examples.html#native
#-keepclasseswithmembernames class * {
#    native <methods>;
#}
#
## keep setters in Views so that animations can still work.
## see http://proguard.sourceforge.net/manual/examples.html#beans
#-keepclassmembers public class * extends android.view.View {
#   void set*(***);
#   *** get*();
#}
#
## We want to keep methods in Activity that could be used in the XML attribute onClick
#-keepclassmembers class * extends android.app.Activity {
#   public void *(android.view.View);
#}
#
## For enumeration classes, see http://proguard.sourceforge.net/manual/examples.html#enumerations
#-keepclassmembers enum * {
#    public static **[] values();
#    public static ** valueOf(java.lang.String);
#}
#
#-keepclassmembers class * implements android.os.Parcelable {
#  public static final android.os.Parcelable$Creator CREATOR;
#}
#
#-keepclassmembers class **.R$* {
#    public static <fields>;
#}
#
## The support library contains references to newer platform versions.
## Don't warn about those in case this app is linking against an older
## platform version.  We know about them, and they are safe.
#-dontwarn android.support.**
#
##有些很特殊的，例如百度地图，你需要添加以下申明：
#
#-keep class com.baidu.** { *; }
# -keep class vi.com.gdi.bgl.android.**{*;}
##使用了反射
#-keepattributes Signature
#-keepattributes EnclosingMethod
#
#
#
##/00000000000000000000/
#
# -keep public class * extends android.app.Fragment
# -keep public class * extends android.app.Activity
# -keep public class * extends android.app.Application
# -keep public class * extends android.app.Service
# -keep public class * extends android.content.BroadcastReceiver
# -keep public class * extends android.content.ContentProvider
# -keep public class * extends android.app.backup.BackupAgentHelper
# -keep public class * extends android.preference.Preference
# -keep public class * extends android.support.v4.**
# -keep public class com.android.vending.licensing.ILicensingService
#
##--以上都是API里边的类，最好都要避免混淆
#
#
#
##//百度sdk的混淆
#-keep class com.baidu.** {*;}
#-keep class vi.com.** {*;}
#-dontwarn com.baidu.**
#
##根据我的经验，一般model最好避免混淆（model无关紧要，不混淆也没多大关系）如：
#
#-keep class ideatc.jinxiang.bean.** { *; }
#
#
#
#
#-dontwarn android.support.v4.**
# -dontwarn org.apache.commons.net.**
#
#-keepclasseswithmembernames class * {
#     native <methods>;
# }
#
#-keepclasseswithmembernames class * {
#     public <init>(android.content.Context, android.util.AttributeSet);
# }
#
#-keepclasseswithmembernames class * {
#     public <init>(android.content.Context, android.util.AttributeSet, int);
# }
#
#-keepclassmembers enum * {
#     public static **[] values();
#     public static ** valueOf(java.lang.String);
# }
#
#-keep class * implements android.os.Parcelable {
#   public static final android.os.Parcelable$Creator *;
# }
#
#-keepclasseswithmembers class * {
#     public <init>(android.content.Context);
# }
#
#-dontshrink
# -dontoptimize
# -dontwarn com.google.android.maps.**
# -dontwarn android.webkit.WebView
#
# -keepattributes Exceptions,InnerClasses,Signature
# -keepattributes *Annotation*
# -keepattributes SourceFile,LineNumberTable
#
#
# -keep public class javax.**
# -keep public class android.webkit.**
#
#
#-keep public class [ideatc.jinxiang].R$*{
#     public static final int *;
# }
