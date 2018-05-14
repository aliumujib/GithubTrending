package com.aliumujib.githubtrending.ui.repodetails

import android.app.PendingIntent
import android.content.*
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.aliumujib.githubtrending.R


/**
 * Created by aliumujib on 14/05/2018.
 *
 * Copy and paste coding made possible by
 *
 * https://stackoverflow.com/questions/44228971/opening-custom-webview-with-powered-by-chrome-with-action-menus
 */
class DetailCustomTab {

    private val TOOLBAR_SHARE_ITEM_ID = 1

    fun openTab(context: Context, url: String) {
        val builder = CustomTabsIntent.Builder()

        enableUrlBarHiding(builder)
        setToolbarColor(context, builder)
        setSecondaryToolbarColor(context, builder)
        setCloseButtonIcon(context, builder)
        setShowTitle(builder)
        setAnimations(context, builder)
        setShareActionButton(context, builder, url)
        addToolbarShareItem(context, builder, url)
        addShareMenuItem(builder)
        addCopyMenuItem(context, builder)

        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(context, Uri.parse(url))
    }

    /* Enables the url bar to hide as the user scrolls down on the page */
    private fun enableUrlBarHiding(builder: CustomTabsIntent.Builder) {
        builder.enableUrlBarHiding()
    }

    /* Sets the toolbar color */
    private fun setToolbarColor(context: Context, builder: CustomTabsIntent.Builder) {
        builder.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
    }

    /* Sets the secondary toolbar color */
    private fun setSecondaryToolbarColor(context: Context, builder: CustomTabsIntent.Builder) {
        builder.setSecondaryToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
    }

    /* Sets the Close button icon for the custom tab */
    private fun setCloseButtonIcon(context: Context, builder: CustomTabsIntent.Builder) {
        builder.setCloseButtonIcon(BitmapFactory.decodeResource(context.resources, R.drawable.ic_close_white_24dp))
    }

    /* Sets whether the title should be shown in the custom tab */
    private fun setShowTitle(builder: CustomTabsIntent.Builder) {
        builder.setShowTitle(true)
    }

    /* Sets animations */
    private fun setAnimations(context: Context, builder: CustomTabsIntent.Builder) {
        builder.setStartAnimations(context, R.anim.slide_in_right, R.anim.slide_out_left)
        builder.setExitAnimations(context, R.anim.slide_in_left, R.anim.slide_out_right)
    }

    /* Sets share action button that is displayed in the Toolbar */
    private fun setShareActionButton(context: Context, builder: CustomTabsIntent.Builder, url: String) {
        val icon = BitmapFactory.decodeResource(context.getResources(), android.R.drawable.ic_menu_share)
        val label = "Share via"

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.putExtra(Intent.EXTRA_TEXT, url)
        shareIntent.type = "text/plain"

        val pendingIntent = PendingIntent.getActivity(context, 0, shareIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        builder.setActionButton(icon, label, pendingIntent)
    }

    /* Adds share item that is displayed in the secondary Toolbar */
    private fun addToolbarShareItem(context: Context, builder: CustomTabsIntent.Builder, url: String) {
        val icon = BitmapFactory.decodeResource(context.getResources(), android.R.drawable.ic_menu_share)
        val label = "Share via"

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.putExtra(Intent.EXTRA_TEXT, url)
        shareIntent.type = "text/plain"

        val pendingIntent = PendingIntent.getActivity(context, 0, shareIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        builder.addToolbarItem(TOOLBAR_SHARE_ITEM_ID, icon, label, pendingIntent)

    }

    /* Adds a default share item to the menu */
    private fun addShareMenuItem(builder: CustomTabsIntent.Builder) {
        builder.addDefaultShareMenuItem()
    }

    /* Adds a copy item to the menu */
    private fun addCopyMenuItem(context: Context, builder: CustomTabsIntent.Builder) {
        val label = "Copy"
        val intent = Intent(context, CopyBroadcastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        builder.addMenuItem(label, pendingIntent)
    }

    class CopyBroadcastReceiver : BroadcastReceiver() {

      override  fun onReceive(context: Context, intent: Intent) {
            val url = intent.dataString

            val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val data = ClipData.newPlainText("Link", url)
          clipboardManager.primaryClip = data

            Toast.makeText(context, "Copied " + url!!, Toast.LENGTH_SHORT).show()
        }
    }
}