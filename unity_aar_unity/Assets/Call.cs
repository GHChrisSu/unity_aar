using UnityEngine;
using System.Collections;

public class Call : MonoBehaviour
{
    string information_ = null;

    void OnGUI()
    {
        //调用显示一个文本为“Hello World!”的Toest
        if (GUI.Button(new Rect(0, 0, 200, 20), "Show Toest - Hello World!"))
        {
            //Unity侧调用Android侧代码
            using (AndroidJavaClass jc = new AndroidJavaClass("com.unity3d.player.UnityPlayer"))
            {
                using (AndroidJavaObject jo = jc.GetStatic<AndroidJavaObject>("currentActivity"))
                {
                    //调用成员方法
                    jo.Call("ShowToast", "Hello World!");
                }
            }
        }

        //获得插件侧的返回字符串
        if (GUI.Button(new Rect(0, 40, 200, 20), "Get Plugin's Information"))
        {
            //Unity侧调用Android侧代码
            using (AndroidJavaClass jc = new AndroidJavaClass("com.unity3d.player.UnityPlayer"))
            {
                using (AndroidJavaObject jo = jc.GetStatic<AndroidJavaObject>("currentActivity"))
                {
                    //调用静态方法
                    information_ = jo.CallStatic<string>("GetInformation");
                }
            }
        }
        //显示返回的字符串
        GUI.Label(new Rect(220, 40, Screen.width - 220, 20), information_);

        if (GUI.Button(new Rect(0, 80, 400, 50), "To Android Acitivity"))
        {
            //Unity侧调用Android侧代码
            using (AndroidJavaClass jc = new AndroidJavaClass("com.unity3d.player.UnityPlayer"))
            {
                using (AndroidJavaObject jo = jc.GetStatic<AndroidJavaObject>("currentActivity"))
                {
                    jo.Call("toAndroidActivity");
                }
            }
        }
    }
}