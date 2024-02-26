import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable, Subscribable } from 'rxjs';
import { ArticleApiService } from '../../services/article-api.service';
import { TopicService } from 'src/app/features/topics/service/topic.service';
import { Article } from '../../interfaces/article.interface';
import { MessageResponse } from 'src/app/interfaces/api/messageResponse.interface';
import { Router } from '@angular/router';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormComponent implements OnInit {

  public articleForm!:FormGroup ;
  public topics$= this.topicService.getAllTopics();
  userId: number;

  constructor(private fb: FormBuilder,private router: Router,
    private matSnackBar: MatSnackBar, private articleService: ArticleApiService, private topicService: TopicService)
     { this.userId = +localStorage.getItem('userID')!}

  ngOnInit(): void {
    this.initForm();
  }
  public submit(): void {
    const article = {titre: this.articleForm?.value.titre,
      content: this.articleForm?.value.content,
      topicId: this.articleForm?.value.topicId,
      userId: this.userId}as Article;
    this.articleService.create(article).subscribe((messageResponse:MessageResponse)=> 
    {this.matSnackBar.open(messageResponse.message, "Close", { duration: 3000});
    this.router.navigate(['sessions']);}
    );
  }
  private initForm(): void {
    this.articleForm = this.fb.group({
      titre: ['', [Validators.required]],
      topicId: [ '',[Validators.required] ],
      content: [ '', [Validators.required,Validators.max(2000)  ]],
    });
  }
}