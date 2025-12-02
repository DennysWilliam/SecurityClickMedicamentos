import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { ReactiveFormsModule, FormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";

import { MatButtonModule } from "@angular/material/button";
import { MatInputModule } from "@angular/material/input";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatCardModule } from "@angular/material/card";
import { MatIconModule } from "@angular/material/icon";
import { MatProgressSpinnerModule } from "@angular/material/progress-spinner";
import { MatToolbarModule } from "@angular/material/toolbar";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { ConsultaComponent } from "./views/search/search.component";
import { UsersComponent } from "./views/users/users.component";
import { PharmaciesComponent } from "./views/pharmacies/pharmacy.component";
import { LoginComponent } from "./views/login/login.component";
import { UserCreateComponent } from "./views/create/create.component";
import { ProfileComponent } from "./views/profile/profile.component";
import { EstoqueComponent } from "./views/estoque/estoque.component";
import { FavoritoComponent } from "./views/favorito/favorito.component";
import { CreateMedicationComponent } from "./views/create-medication/create-medication.component";

@NgModule({
  declarations: [
    AppComponent,
    ConsultaComponent,
    UsersComponent,
    PharmaciesComponent,
    LoginComponent,
    UserCreateComponent,
    ProfileComponent,
    EstoqueComponent,
    FavoritoComponent,
    CreateMedicationComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    MatCardModule,
    MatIconModule,
    MatProgressSpinnerModule,
    MatToolbarModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
