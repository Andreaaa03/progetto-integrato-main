import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-post-articolo',
  templateUrl: './post-articolo.component.html',
  styleUrls: ['./post-articolo.component.css']
})
export class PostArticoloComponent {
  @Input() extended: boolean = true;
}
