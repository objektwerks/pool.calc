package pool.dialog

import scalafx.scene.control.{Dialog, TextField}
import scalafx.stage.Stage

import pool.Context

final case class RegisterLogin(registerEmailAddress: String = "",
                               loginEmailAddress: String = "",
                               loginPin: String = "")

final class RegisterLoginDialog(owner: Stage, context: Context) extends Dialog[RegisterLogin]:
  initOwner(owner)
  title = context.windowTitle
  headerText = context.dialogRegisterLogin

  val registerLogin = RegisterLogin()

  val registerEmailAddressTextField = new TextField:
    text = registerLogin.registerEmailAddress

  val loginEmailAddressTextField = new TextField:
    text = registerLogin.loginEmailAddress

  val loginPinTextField = new TextField:
    text = registerLogin.loginPin