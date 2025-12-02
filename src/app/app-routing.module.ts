import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { ConsultaComponent } from "./views/search/search.component";
import { UsersComponent } from "./views/users/users.component";
import { PharmaciesComponent } from "./views/pharmacies/pharmacy.component";
import { LoginComponent } from "./views/login/login.component";
import { UserCreateComponent } from "./views/create/create.component";
import { ProfileComponent } from "./views/profile/profile.component";
import { EstoqueComponent } from "./views/estoque/estoque.component";
import { FavoritoComponent } from "./views/favorito/favorito.component";
import { CreateMedicationComponent } from "./views/create-medication/create-medication.component";

const routes: Routes = [
  { path: "", component: ConsultaComponent },
  { path: "search", component: ConsultaComponent },
  { path: "users", component: UsersComponent },
  { path: "pharmacies", component: PharmaciesComponent },
  { path: "login", component: LoginComponent },
  { path: "create", component: UserCreateComponent },
  { path: "profile", component: ProfileComponent },
  {
    path: "estoque",
    component: EstoqueComponent,
    children: [
      { path: "adicionar", component: EstoqueComponent },
      { path: "editar/:id", component: EstoqueComponent },
    ],
  },
  { path: "favorito", component: FavoritoComponent },
  { path: "create-medication", component: CreateMedicationComponent },
  { path: "**", component: LoginComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
