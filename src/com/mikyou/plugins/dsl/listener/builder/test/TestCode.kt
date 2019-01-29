package com.mikyou.plugins.dsl.listener.builder.test

class TestCode {
    private lateinit var mListener: ListenerBuilder

    fun setListener(listenerBuilder: ListenerBuilder.() -> Unit) {
        mListener = ListenerBuilder().also(listenerBuilder)
    }

    inner class ListenerBuilder {
        internal var mAudioFinishAction: (() -> Unit)? = null
        internal var mAudioSeekAction: ((Long, Long) -> Unit)? = null
        internal var mAudioPlayAction: (() -> Unit)? = null
        internal var mAudioPauseAction: (() -> Unit)? = null

        fun onAudioFinish(action: () -> Unit) {
            mAudioFinishAction = action
        }

        fun onAudioSeek(action: (Long, Long) -> Unit) {
            mAudioSeekAction = action
        }

        fun onAudioPlay(action: () -> Unit) {
            mAudioPlayAction = action
        }

        fun onAudioPause(action: () -> Unit) {
            mAudioPauseAction = action
        }
    }

}