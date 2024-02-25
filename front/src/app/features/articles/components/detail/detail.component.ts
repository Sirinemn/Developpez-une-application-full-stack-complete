import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ArticleApiService } from '../../services/article-api.service';
import { Article } from '../../interfaces/article.interface';
import { CommentsServiceService } from '../../services/comments-service.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CommentResponse } from '../../interfaces/api/commentResponse.interface';
import { CommentRequest } from '../../interfaces/api/commentRequest.interface';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.scss']
})
export class DetailComponent implements OnInit {
  public article: Article | undefined;
  public articleId = this.route.snapshot.paramMap.get('id')!;

  public userId: string;
  public commentForm!: FormGroup;
  public  comments$ = this.commentService.allcomments(this.articleId);
  
  constructor( private route: ActivatedRoute, private articleApiService: ArticleApiService, private commentService: CommentsServiceService,
    private matSnackBar: MatSnackBar, private fb: FormBuilder) {
  this.userId = localStorage.getItem('userID')!,
  this.initCommentForm()}

  ngOnInit(): void {
    this.articleApiService.detail(this.articleId).subscribe((article: Article)=>this.article = article)
  }
  public back() {
    window.history.back();
  }
  public addComment(): void {
    const comment = {
      userId: +this.userId,
      articleId: this.article?.id,
      content: this.commentForm.value.comment
    } as CommentRequest;
    this.commentService.send(comment).subscribe((commentResponse: CommentResponse)=>
    {this.matSnackBar.open(commentResponse.message, "Close", { duration: 3000});
  });
  }
  private initCommentForm() {
    this.commentForm = this.fb.group({
      comment: ['', [Validators.required, Validators.min(10)]],
    });
  }
}
