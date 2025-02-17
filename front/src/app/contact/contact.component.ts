import { Component } from "@angular/core";
import {FormBuilder, ReactiveFormsModule, Validators} from "@angular/forms";
import { Router } from '@angular/router';
import {NgIf} from "@angular/common";
import {Button} from "primeng/button";
import {InputTextareaModule} from "primeng/inputtextarea";

@Component({
  selector: "app-contact",
  templateUrl: "./contact.component.html",
  standalone: true,
  imports: [
    NgIf,
    Button,
    ReactiveFormsModule,
    InputTextareaModule
  ],
  styleUrls: ["./contact.component.scss"]
})
export class ContactComponent {
  // Reactive Form for Contact Form
  contactForm = this.fb.group({
    email: ["", [Validators.required, Validators.email]],
    message: ["", [Validators.required, Validators.maxLength(300)]],
  });

  constructor(private fb: FormBuilder, private router: Router) {}

  onSubmit() {
    if (this.contactForm.valid) {
      alert("Demande de contact envoyée avec succès");
      // Reset form after submission
      this.contactForm.reset();
      this.router.navigate(['/']);
    } else {
      alert("Veuillez remplir correctement le formulaire.");
    }
  }
}
