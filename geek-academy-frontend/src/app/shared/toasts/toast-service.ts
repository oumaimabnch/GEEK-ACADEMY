import { Injectable, TemplateRef } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class ToastService {
  toasts: any[] = [];

  showStandard(message: string) {
    this.show(message);
  }

  showSuccess(message: string) {
    this.show(message, {
      classname: 'bg-success text-light',
      delay: 10000,
    });
  }

  showDanger(message: string) {
    this.show(message, {
      classname: 'bg-danger text-light',
      delay: 15000,
    });
  }

  showCustomToast(message: string) {
    this.show(message, {
      classname: 'bg-info text-light',
      delay: 3000,
      autohide: true,
    });
  }

  show(textOrTpl: string | TemplateRef<any>, options: any = {}) {
    this.toasts.push({ textOrTpl, ...options });
  }

  remove(toast: any) {
    this.toasts = this.toasts.filter((t) => t !== toast);
  }
}
