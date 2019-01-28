package com.mikyou.plugins.dsl.listener.builder.helper

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiDocumentManager

object InsertCodeHelper {
    fun doInsert(generateCode: String, editor: Editor) {
        val document: Document = editor.document
        WriteCommandAction.runWriteCommandAction(editor.project) {
            document.replaceString(editor.selectionModel.selectionStart, editor.selectionModel.selectionEnd, generateCode)
            if (editor.project != null) {
                PsiDocumentManager.getInstance(requireNotNull(editor.project)).commitDocument(document)
            }
        }
        editor.selectionModel.removeSelection()
    }
}