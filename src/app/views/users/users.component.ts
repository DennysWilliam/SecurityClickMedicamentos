import { Component, OnInit } from "@angular/core";
import { UserService } from "../../services/user.service";
import { User } from "../../domain/model/user";

@Component({
  selector: "app-users",
  templateUrl: "./users.component.html",
  styleUrls: ["./users.component.css"],
})
export class UsersComponent implements OnInit {
  users: User[] = [];
  isLoading = false;
  selectedUser: User | null = null;
  showForm = false;

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.isLoading = true;
    this.userService.getAllUsers().subscribe({
      next: (users) => {
        this.users = users;
        this.isLoading = false;
      },
      error: (error) => {
        console.error("Erro ao carregar usuários:", error);
        this.isLoading = false;
      },
    });

    console.log(this.users);
  }

  onEdit(user: User): void {
    this.selectedUser = { ...user };
    this.showForm = true;
  }

  onDelete(id: number): void {
    if (confirm("Tem certeza que deseja excluir este usuário?")) {
      this.userService.deleteUser(id).subscribe({
        next: () => {
          this.loadUsers();
        },
        error: (error) => {
          console.error("Erro ao excluir usuário:", error);
        },
      });
    }
  }

  onSave(user: User): void {
    if (user.id) {
      this.userService.updateUser(user.id, user).subscribe({
        next: () => {
          this.loadUsers();
          this.closeForm();
        },
        error: (error) => {
          console.error("Erro ao atualizar usuário:", error);
        },
      });
    } else {
      const updateState = {
        ...user,
        state: user.uf.toUpperCase(),
      };
      this.userService.createUser(updateState).subscribe({
        next: () => {
          this.loadUsers();
          this.closeForm();
        },
        error: (error) => {
          console.error("Erro ao criar usuário:", error);
        },
      });
    }
  }

  onNew(): void {
    this.selectedUser = {
      id: 0,
      nome: "",
      email: "",
      cpf: "",
      telefone: "",
      municipio: "",
      uf: "",
      tipo: "",
      senha: "",
      endereco: "",
    };
    this.showForm = true;
  }

  closeForm(): void {
    this.showForm = false;
    this.selectedUser = null;
  }
}
